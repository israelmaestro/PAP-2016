package br.com.shepherd.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.com.shepherd.entity.Email;
import br.com.shepherd.entity.Pessoa;
import br.com.shepherd.entity.PessoaAtendimento;
import br.com.shepherd.entity.PessoaCelula;
import br.com.shepherd.entity.PessoaSede;
import br.com.shepherd.entity.Telefone;
import br.com.shepherd.service.util.EnderecoUtils;
import br.com.shepherd.service.util.JSFUtil;
import br.com.shepherd.service.util.PessoaUtils;

@Stateless
public class VisitanteService{
	@PersistenceContext(name = "ShepherdDB")
	private EntityManager	entityManager;

	PessoaUtils				pessoaUtils	= new PessoaUtils();
	EnderecoUtils			enderecoUtils	= new EnderecoUtils();

	public VisitanteService(){

	}

	/**
	 * Realiza o cadastro do visitante da Célula ou da Sede
	 *
	 * @param pVisitante
	 * @return
	 * @throws Exception
	 */
	public Pessoa cadastrar(Pessoa pVisitante) throws Exception{
		// Ativar novo visitante
		pVisitante.setAtiva(true);

		pVisitante = pessoaUtils.consistir(pVisitante);

		if(null == pVisitante.getEndereco()
			|| enderecoUtils.isEmpty(pVisitante.getEndereco())){
			throw new Exception("Endereço: Preencha os campos obrigatórios!");
		}

		if(null == pVisitante.getTelefones()
			|| pVisitante	.getTelefones()
							.isEmpty()){
			pVisitante.setTelefones(new ArrayList<Telefone>());
			throw new Exception("O cadastro precisa ter pelo menos 1 telefone.");
		}

		if(null == pVisitante.getEmails()
			|| pVisitante	.getEmails()
							.isEmpty()){
			pVisitante.setEmails(new ArrayList<Email>());
			throw new Exception("O cadastro precisa ter pelo menos 1 email.");
		}

		Pessoa existente = buscaPessoaUnica(pVisitante.getNome(), pVisitante.getSobrenome(),
											pVisitante.getDataNasc(), pVisitante.getRg(),
											pVisitante.getCpf(), pVisitante.isSexo());

		if(null == existente){
			entityManager.persist(pVisitante);
		} else{
			throw new Exception("Pessoa já existente no sistema.\nO conjunto dos campos Nome, Sobrenome, "
								+ "Data de Nascimento, RG, CPF e Sexo não pode coincidir.");
		}

		return pVisitante;
	}

	/**
	 * Realiza a alteração de um Visitante
	 *
	 * @param pVisitante
	 * @return
	 */
	public Pessoa alterar(Pessoa pVisitante){
		entityManager.merge(pVisitante);

		return pVisitante;
	}

	/**
	 * Lista todas as pessoas do sistema
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarTodos(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "
											+ "ORDER BY dbPessoa.nome, dbPessoa.sobrenome")
							.getResultList();
	}

	/**
	 * Lista os visitantes das células
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PessoaCelula> listarVisitantesCelulas(){
		List<PessoaCelula> VisitantesCelulas = new ArrayList<PessoaCelula>();

		try{
			Query query = entityManager.createQuery("FROM PessoaCelula dbPessoaCelula "
													+ "WHERE UPPER(dbPessoaCelula.participacao) = UPPER(:p1) "
													+ "ORDER BY dbPessoaCelula.pessoa.nome, dbPessoaCelula.pessoa.sobrenome");
			query.setParameter("p1", "VISITANTE");

			VisitantesCelulas = query.getResultList();

			return VisitantesCelulas;
		} catch(NoResultException n){
			return null;
		}
	}

	/**
	 * Lista os visitantes das sedes
	 *
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PessoaSede> listarVisitantesSedes(){
		List<PessoaSede> VisitantesSedes = new ArrayList<PessoaSede>();

		try{
			Query query = entityManager.createQuery("FROM PessoaSede dbPessoaSede "
													+ "WHERE UPPER(dbPessoaSede.participacao) = UPPER(:p1) "
													+ "ORDER BY dbPessoaSede.pessoa.nome, dbPessoaSede.pessoa.sobrenome");
			query.setParameter("p1", "VISITANTE");

			VisitantesSedes = query.getResultList();

			for(PessoaSede pessoaSede : VisitantesSedes){
				Hibernate.initialize(pessoaSede.getPessoa().getPessoasAtendimentos());
			}

			return VisitantesSedes;
		} catch(NoResultException n){
			return null;
		}
	}

	/**
	 * Retorna o status do atendimento
	 *
	 * @param pPessoa
	 * @return
	 */
	public String getStatusAtendimento(Pessoa pPessoa){
		String status = "";

		try{
			for(PessoaAtendimento pessoaAtendimento : pPessoa.getPessoasAtendimentos()){
				if(pPessoa.getPessoasAtendimentos().size() < 2){
					if(pPessoa.getPessoasAtendimentos().isEmpty()){
						status = "Não";
					} else{
						status = pessoaAtendimento.getAtendimento().getStatus();
					}
				} else{
					if(!pessoaAtendimento	.getAtendimento().getStatus()
							.equals(JSFUtil.getProperty("countAtendimentoStatus"))){
						status = pessoaAtendimento.getAtendimento().getStatus();
					} else{
						status = "Finalizado(s)";
					}

				}
			}

		} catch(IOException e){
			// Nada a fazer
			status = "Não";
		}
		return status;
	}

	// @SuppressWarnings("unchecked")
	// public List<Pessoa> listarExcluindo(Pessoa pPai, Pessoa pMae){
	// Query query = entityManager.createQuery("FROM Pessoa dbPessoa"
	// + (null != pPai
	// ? " WHERE dbPessoa.id <> :p1"
	// + (null != pMae
	// ? " AND dbPessoa.id <> :p2"
	// : "")
	// : null != pMae
	// ? " WHERE dbPessoa.id <> :p2"
	// : ""
	// )
	// + " ORDER BY dbPessoa.nome");
	// if(null != pPai){
	// query.setParameter("p1", pPai.getId());
	// }
	//
	// if(null != pMae){
	// query.setParameter("p2", pMae.getId());
	// }
	//
	// try{
	// return query.getResultList();
	// } catch(NoResultException n){
	// return null;
	// }
	// }

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarHomens(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "AND dbPessoa.sexo = true "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarHomensSolteiros(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "AND dbPessoa.sexo = true "
											+ "AND dbPessoa.casada = false "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarMulheres(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "AND dbPessoa.sexo = false "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarMulheresSolteiras(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "AND dbPessoa.sexo = false "
											+ "AND dbPessoa.casada = false "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	// @SuppressWarnings("unchecked")
	// public List<Lider> listarLideres(){
	// return entityManager.createQuery("FROM Lider dbLider ").getResultList();
	// }

	// @SuppressWarnings("unchecked")
	// public List<Coordenador> listarCoordenadores(){
	// return entityManager.createQuery("FROM Coordenador dbCoordenador
	// ").getResultList();
	// }

	// @SuppressWarnings("unchecked")
	// public List<Ministro> listarPresidentes(){
	// return entityManager.createQuery("FROM Ministro dbMinistro WHERE
	// dbMinistro.isPresident = true")
	// .getResultList();
	// }

	public void excluir(Pessoa pPessoa){
		pPessoa = entityManager.merge(pPessoa);
		entityManager.remove(pPessoa);
	}

	public Pessoa buscaPessoaUnica(	String pNome, String pSobrenome, Date pDataNasc, String pRg,
									String pCpf, boolean pSexo){
		Query query = entityManager.createQuery("FROM Pessoa dbPessoa "
												+ "WHERE UPPER(dbPessoa.nome) = UPPER(:p1) "
												+ "AND UPPER(dbPessoa.sobrenome) = UPPER(:p2) "
												+ (null == pDataNasc	? "AND dbPessoa.dataNasc IS NULL "
																		: "AND dbPessoa.dataNasc = :p3 ")
												+ (null == pRg	? "AND dbPessoa.rg IS NULL "
																: "AND dbPessoa.rg = :p4 ")
												+ (null == pCpf	? "AND dbPessoa.cpf IS NULL "
																: "AND dbPessoa.cpf = :p5 ")
												+ "AND dbPessoa.sexo = :p6");
		query.setParameter("p1", pNome);
		query.setParameter("p2", pSobrenome);

		if(null != pDataNasc){
			query.setParameter("p3", pDataNasc);
		}

		if(null != pRg){
			query.setParameter("p4", pRg);
		}

		if(null != pCpf){
			query.setParameter("p5", pCpf);
		}

		query.setParameter("p6", pSexo);

		try{
			return (Pessoa) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}

	public Pessoa buscaCriterioStr(String pTabela, String pCampo, String pValor){
		Query query = entityManager.createQuery("FROM "+ pTabela
												+ " db"
												+ pTabela
												+ " "
												+ "WHERE "
												+ (null == pValor	? "db"+ pTabela
																		+ "."
																		+ pCampo
																		+ " is null "
																	: "db"+ pTabela
																		+ "."
																		+ pCampo
																		+ " = :p1"));
		if(null != pValor){
			query.setParameter("p1", pValor);
		}

		try{
			return (Pessoa) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
