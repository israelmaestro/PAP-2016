package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Sede;

@Stateless
public class SedeService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public SedeService(){
	}

	/**
	 * Efetua o cadastro da sede no sistema
	 *
	 * @param pSede
	 * @return Sede gravada com o ID gerado pelo Hibernate
	 * @throws Exception
	 */
	public Sede cadastrar(Sede pSede) throws Exception{

		// TODO: Aplicar regras para preenchimento de endereço
		// Logradouro:
		// Obrigatório quando CEP preenchido
		//
		// Número/Cidade/País:
		// Obrigatório quando CEP preenchido
		// OU quando Logradouro não é endereço de GPS

		// TODO: Aplicar regras para valores padrão
		// isMainChurch = false
		// isActive = true
		// Campos em branco recebem null

		if(null == pSede.getNome()
			|| pSede.getNome()
					.equals("")){
			throw new Exception("O cadastro possui campos obrigatórios não preenchidos!");
		}

		if(null == pSede.getCnpj() || pSede.getCnpj().equals("")){
			pSede.setCnpj(null);
		}

		pSede.setAtiva(true);

		Sede existente = buscaCriterio("Sede", "nome", pSede.getNome(), "cnpj",
										pSede.getCnpj());

		if(existente == null){
			entityManager.persist(pSede);

			return pSede;
		} else{
			throw new Exception("Sede “"+ pSede.getNome()
								+ (null != pSede.getCnpj()	? "” com o CNPJ “" + pSede.getCnpj()
															: "")
								+ "” já está cadastrada!");
		}

	}

	public Sede alterar(Sede pSede){
		entityManager.merge(pSede);

		return pSede;
	}

	@SuppressWarnings("unchecked")
	public List<Sede> listar(){
		return entityManager.createQuery("FROM Sede dbSede " + "ORDER BY dbSede.nome")
							.getResultList();
	}

	public void excluir(Sede pSede){
		pSede = entityManager.merge(pSede);
		entityManager.remove(pSede);
	}

	/**
	 * Método de busca no banco de dados
	 *
	 * 1 tabela e 1 campo
	 *
	 * @param pTabela
	 * @param pCampo
	 * @param pValor
	 * @return
	 */
	public Sede buscaCriterio(String pTabela, String pCampo, String pValor){
		Query query = entityManager.createQuery("FROM "+ pTabela
												+ " db"
												+ pTabela
												+ " WHERE UPPER(db"
												+ pTabela
												+ "."
												+ pCampo
												+ ") = UPPER(:p1)");
		query.setParameter("p1", pValor);

		try{
			return (Sede) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}

	/**
	 * Método de busca no banco de dados
	 *
	 * 1 tabela e 2 campos
	 *
	 * @param pTabela
	 * @param pCampo1
	 * @param pValor1
	 * @param pCampo2
	 * @param pValor2
	 * @return
	 */
	public Sede buscaCriterio(	String pTabela, String pCampo1, String pValor1,
								String pCampo2, String pValor2){
		Query query = entityManager.createQuery("FROM "+ pTabela
												+ " db"
												+ pTabela
												+ " WHERE "
												+ (pValor1 == null	? "db"+ pTabela
																		+ "."
																		+ pCampo1
																		+ " IS NULL"
																	: "UPPER(db"+ pTabela
																		+ "."
																		+ pCampo1
																		+ ") = UPPER(:p1)")
												+ " AND "
												+ (pValor2 == null	? "db"+ pTabela
																		+ "."
																		+ pCampo2
																		+ " IS NULL"
																	: "UPPER(db"+ pTabela
																		+ "."
																		+ pCampo2
																		+ ") = UPPER(:p2)"));
		if(null != pValor1){
			query.setParameter("p1", pValor1);
		}

		if(null != pValor2){
			query.setParameter("p2", pValor2);
		}
		try{
			return (Sede) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
