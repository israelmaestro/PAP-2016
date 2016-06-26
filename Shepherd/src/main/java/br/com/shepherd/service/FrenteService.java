package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.com.shepherd.entity.Frente;

@Stateless
public class FrenteService {

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public FrenteService() {
	}

	public Frente cadastrar(Frente pFrente) throws Exception {

		//Frente existente = buscaCriterio("Frente", "nome", pFrente.getNome());
		Frente existente = buscaCriterio(pFrente.getNome());
		
		if (existente == null) {
			entityManager.persist(pFrente);

			return pFrente;
		} else {
			throw new Exception("Frente “" + pFrente.getNome() + "” já está cadastrada! Escolha outro nome.");
		}

	}

	public Frente alterar(Frente pFrente) throws Exception {
		// pFrente = fre

		//Frente existente = buscaCriterio("Frente", "nome", pFrente.getNome());
		Frente existente = buscaCriterio(pFrente.getNome());
		
		if (null != existente) {

			if (existente.getId().equals(pFrente.getId())) {
				entityManager.merge(pFrente);

				return pFrente;
			} else {
				throw new Exception("Frente “" + pFrente.getNome() + "” já está cadastrada! Escolha outro nome.");
			}
		} else {
			entityManager.merge(pFrente);

			return pFrente;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Frente> listar() {
		List<Frente> tFrente = entityManager.createQuery("FROM Frente dbFrente " + "ORDER BY dbFrente.nome")
				.getResultList();

		for (Frente pFrente : tFrente) {
			Hibernate.initialize(pFrente.getCelulas());
		}
		return tFrente;
	}

	@SuppressWarnings("unchecked")
	public List<Frente> listarTipoCelula() {
		return entityManager
				.createQuery("FROM Frente dbFrente " + "WHERE dbFrente.isCell is TRUE " + "ORDER BY dbFrente.nome")
				.getResultList();
	}

	public void excluir(Frente pFrente) {
		pFrente = entityManager.merge(pFrente);
		entityManager.remove(pFrente);
	}

	// public Frente buscaCriterio(String pTabela, String pCampo, String
	// pValor){
	// Query query = entityManager.createQuery("FROM "+ pTabela + " db" +
	// pTabela
	// + " WHERE UPPER(db" + pTabela
	// + "." + pCampo + ") = UPPER(:p1)");
	// query.setParameter("p1", pValor);
	//
	// try{
	// return (Frente) query.getSingleResult();
	// } catch(NoResultException n){
	// return null;
	// }
	// }

	public Frente buscaCriterio(String pNome) {
		Query query = entityManager.createQuery("FROM Frente dbFrente " + " WHERE UPPER(dbFrente.nome) = UPPER(:p1)");

		query.setParameter("p1", pNome);

		try {
			return (Frente) query.getSingleResult();
		} catch (NoResultException n) {
			return null;
		}
	}
}
