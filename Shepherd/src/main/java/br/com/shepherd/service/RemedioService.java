package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Remedio;

@Stateless
public class RemedioService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public RemedioService(){
	}

	public Remedio cadastrar(Remedio pRemedio) throws Exception{
		if(!pRemedio.getTarja().toUpperCase().equals("#FF0000")
			&& !pRemedio.getTarja().toUpperCase().equals("#000000")){
			pRemedio.setTarja(null);
		}

		Remedio existente = buscaCriterio(	"Remedio", "principioAtivo",
											pRemedio.getPrincipioAtivo());

		if(null == existente){
			entityManager.persist(pRemedio);

			return pRemedio;
		} else{
			throw new Exception("Princípio ativo “"+ pRemedio.getPrincipioAtivo()
								+ "” já está cadastrado!");
		}
	}

	public Remedio alterar(Remedio pRemedio){
		entityManager.merge(pRemedio);

		return pRemedio;
	}

	@SuppressWarnings("unchecked")
	public List<Remedio> listar(){
		return entityManager.createQuery("FROM Remedio dbRemedio "
											+ "ORDER BY dbRemedio.nome")
							.getResultList();
	}

	public void excluir(Remedio pRemedio){
		pRemedio = entityManager.merge(pRemedio);
		entityManager.remove(pRemedio);
	}

	public Remedio buscaCriterio(String pTabela, String pCampo, String pValor){
		Query query = entityManager.createQuery("FROM "+ pTabela + " db" + pTabela
												+ " WHERE UPPER(db" + pTabela + "." + pCampo
												+ ") = UPPER(:p1)");
		query.setParameter("p1", pValor);
		try{
			return (Remedio) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
