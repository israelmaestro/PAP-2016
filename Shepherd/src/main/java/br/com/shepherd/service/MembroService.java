package br.com.shepherd.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Coordenador;
import br.com.shepherd.entity.Lider;
import br.com.shepherd.entity.Ministro;
import br.com.shepherd.entity.Pessoa;

@Stateless
public class MembroService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public MembroService(){

	}

	public Pessoa cadastrar(Pessoa pPessoa) throws Exception{

		// Tornar ativo
		pPessoa.setAtivo(true);
		pPessoa.setMember(true);

		// Consistir dados de casamento
		if(pPessoa.isMarried()){
			if(null == pPessoa.getConjuge()){ throw new Exception("Desative a opção “Casado” ou selecione um cônjuge!"); }

			if(null == pPessoa.getDataCasamento() || pPessoa.getDataCasamento().equals("")){
				pPessoa.setDataCasamento(null);
			}
		} else{
			pPessoa.setConjuge(null);
		}

		// Eliminando espaços vazios
		pPessoa.setNome(pPessoa.getNome().trim());
		pPessoa.setSobrenome(pPessoa.getSobrenome().trim());

		if(null != pPessoa.getRg()){
			if(!pPessoa.getRg().equals("")){
				pPessoa.setRg(pPessoa.getRg().trim());
			} else{
				pPessoa.setRg(null);
			}
		}

		if(null != pPessoa.getCpf()){
			if(!pPessoa.getCpf().equals("")){
				pPessoa.setCpf(pPessoa.getCpf().trim());
			} else{
				pPessoa.setCpf(null);
			}
		}

		// Consistir endereço
		if(!pPessoa.isGpsAddress()){
			if(null == pPessoa.getEnderecoCep() || pPessoa.getEnderecoCep().equals("")){
				pPessoa.setEnderecoCep(null);
			} else{
				pPessoa.setEnderecoCep(pPessoa.getEnderecoCep().trim());
			}

			if(null == pPessoa.getEnderecoLogradouro()
				|| pPessoa.getEnderecoLogradouro().equals("")){
				pPessoa.setEnderecoLogradouro(null);
				pPessoa.setEnderecoNumero(null);
				pPessoa.setEnderecoComplemento(null);
				pPessoa.setEnderecoBairro(null);
				pPessoa.setEnderecoCidade(null);
				pPessoa.setEnderecoEstado(null);
				pPessoa.setEnderecoPais(null);
			} else{
				pPessoa.setEnderecoLogradouro(pPessoa.getEnderecoLogradouro().trim());

				if(null == pPessoa.getEnderecoNumero() || pPessoa.getEnderecoNumero().equals("")){
					// Endereço sem número
					throw new Exception("Campo Nº obrigatório quando há endereço!");
				}

				if(null == pPessoa.getEnderecoComplemento()
					|| pPessoa.getEnderecoComplemento().equals("")){
					// Campo vazio
					pPessoa.setEnderecoComplemento(null);
				} else{
					pPessoa.setEnderecoComplemento(pPessoa.getEnderecoComplemento().trim());
				}

				if(null == pPessoa.getEnderecoCidade() || pPessoa.getEnderecoCidade().equals("")){
					// Campo vazio
					pPessoa.setEnderecoCidade(null);
				} else{
					pPessoa.setEnderecoCidade(pPessoa.getEnderecoCidade().trim());
				}

				if(null == pPessoa.getEnderecoEstado() || pPessoa.getEnderecoEstado().equals("")){
					// Campo vazio
					pPessoa.setEnderecoEstado(null);
				} else{
					pPessoa.setEnderecoEstado(pPessoa.getEnderecoEstado().trim());
				}

				if(null == pPessoa.getEnderecoPais() || pPessoa.getEnderecoPais().equals("")){
					// Campo vazio
					pPessoa.setEnderecoPais(null);
				} else{
					pPessoa.setEnderecoPais(pPessoa.getEnderecoPais().trim());
				}
			}
		} else{
			if(null == pPessoa.getEnderecoLogradouro()
				|| pPessoa.getEnderecoLogradouro().equals("")){
				// Coordenadas de GPS nulas
				throw new Exception("Coordenadas de GPS: Campo obrigatório!");
			} else{
				pPessoa.setEnderecoLogradouro(pPessoa.getEnderecoLogradouro().trim());
				pPessoa.setEnderecoCep(null);
				pPessoa.setEnderecoNumero(null);
				pPessoa.setEnderecoComplemento(null);
				pPessoa.setEnderecoBairro(null);
				pPessoa.setEnderecoCidade(null);
				pPessoa.setEnderecoEstado(null);
				pPessoa.setEnderecoPais(null);
			}
		}

		entityManager.persist(pPessoa);

		if(pPessoa.isMarried()){
			Pessoa tConjuge = pPessoa.getConjuge();

			tConjuge.setMarried(true);
			tConjuge.setConjuge(pPessoa);
			tConjuge.setDataCasamento(pPessoa.getDataCasamento());

			entityManager.merge(tConjuge);
		}

		return pPessoa;
	}

	public Pessoa alterar(Pessoa pPessoa){
		entityManager.merge(pPessoa);

		return pPessoa;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listar(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "WHERE dbPessoa.isMember = true "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarExcluindo(Pessoa pPai, Pessoa pMae){
		Query query = entityManager.createQuery("FROM Pessoa dbPessoa"
											+ (null != pPai
													?	" WHERE dbPessoa.id <> :p1"
													 	+ (null != pMae
													 			? " AND dbPessoa.id <> :p2"
													 			: "")
													:	null != pMae
															? " WHERE dbPessoa.id <> :p2"
															: ""
												)
											+ " ORDER BY dbPessoa.nome");
		if(null != pPai){
			query.setParameter("p1", pPai.getId());
		}

		if(null != pMae){
			query.setParameter("p2", pMae.getId());
		}

		try{
			return query.getResultList();
		} catch(NoResultException n){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarHomens(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "WHERE dbPessoa.isMember = true "
											+ "AND dbPessoa.sexo = true "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarHomensSolteiros(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "WHERE dbPessoa.isMember = true "
											+ "AND dbPessoa.sexo = true "
											+ "AND dbPessoa.isMarried = false "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarMulheres(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "WHERE dbPessoa.isMember = true "
											+ "AND dbPessoa.sexo = false "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarMulheresSolteiras(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "+ "WHERE dbPessoa.isMember = true "
											+ "AND dbPessoa.sexo = false "
											+ "AND dbPessoa.isMarried = false "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Lider> listarLideres(){
		return entityManager.createQuery("FROM Lider dbLider ").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Coordenador> listarCoordenadores(){
		return entityManager.createQuery("FROM Coordenador dbCoordenador ").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Ministro> listarPresidentes(){
		return entityManager.createQuery("FROM Ministro dbMinistro WHERE dbMinistro.isPresident = true")
							.getResultList();
	}

	public void excluir(Pessoa pResponsavel){
		pResponsavel = entityManager.merge(pResponsavel);
		entityManager.remove(pResponsavel);
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
												+ "AND UPPER(dbPessoa.sexo) = UPPER(:p6) ");
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
