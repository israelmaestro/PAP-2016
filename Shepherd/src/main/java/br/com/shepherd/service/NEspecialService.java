package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.NEspecial;;

@Stateless
public class NEspecialService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public NEspecialService(){
	}

	public NEspecial cadastrar(NEspecial pNEspecial) throws Exception{

		NEspecial existente = buscaNome(pNEspecial.getNome());

		if(existente == null){
			entityManager.persist(pNEspecial);

			return pNEspecial;
		} else{
			throw new Exception("Necessidade especial '"
								+ pNEspecial.getNome()
								+ "' já está cadastrada!");
		}

	}

	public NEspecial alterar(NEspecial pNEspecial){
		entityManager.merge(pNEspecial);

		return pNEspecial;
	}

	@SuppressWarnings("unchecked")
	public List<NEspecial> listar(){
		return entityManager.createQuery("FROM NEspecial dbNEspecial "
											+ "ORDER BY dbNEspecial.nome")
							.getResultList();
	}

	public void excluir(NEspecial pNEspecial){
		pNEspecial = entityManager.merge(pNEspecial);
		entityManager.remove(pNEspecial);
	}

	public NEspecial buscaNome(String pNome){
		Query query = entityManager.createQuery("FROM NEspecial dbNEspecial "
												+ "WHERE UPPER(dbNEspecial.nome) = UPPER(:p1)");
		query.setParameter("p1", pNome);
		try{
			return (NEspecial) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
