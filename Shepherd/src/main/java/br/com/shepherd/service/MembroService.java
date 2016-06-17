package br.com.shepherd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Pessoa;
import br.com.shepherd.entity.PessoaCelula;
import br.com.shepherd.service.util.PessoaUtils;

@Stateless
public class MembroService{
	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	PessoaUtils				pessoaUtils = new PessoaUtils();

	public MembroService(){

	}

	public Pessoa cadastrar(Pessoa pPessoa) throws Exception{
		// Ativar novo membro
		pPessoa.setAtiva(true);

		pPessoa = pessoaUtils.consistir(pPessoa);

		entityManager.persist(pPessoa);

		return pPessoa;
	}

	public Pessoa alterar(Pessoa pPessoa){
		entityManager.merge(pPessoa);

		return pPessoa;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarTodos(){
		return entityManager.createQuery("FROM Pessoa dbPessoa " + "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaCelula> listar(){
		List<PessoaCelula> MembrosCelulas = new ArrayList<PessoaCelula>();

		try{
			Query query = entityManager.createQuery("FROM PessoaCelula dbPessoaCelula "
													+ "WHERE UPPER(dbPessoaCelula.participacao) = UPPER(:p1) "
													+ "ORDER BY dbPessoaCelula.pessoa.nome, dbPessoaCelula.pessoa.sobrenome");
			query.setParameter("p1", "MEMBRO");

			MembrosCelulas = query.getResultList();

			return MembrosCelulas;
		} catch(NoResultException n){
			return null;
		}
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
