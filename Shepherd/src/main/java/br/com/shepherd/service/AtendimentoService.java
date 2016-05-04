package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.shepherd.entity.Atendimento;;

@Stateless
public class AtendimentoService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public AtendimentoService(){
	}

	public Atendimento cadastrar(Atendimento pAtendimento) throws Exception{
		try{
			entityManager.persist(pAtendimento);

			return pAtendimento;
		} catch(Exception e){
			throw new Exception("Problema ao cadastrar atendimento!");
		}

	}

	public Atendimento alterar(Atendimento pAtendimento){
		entityManager.merge(pAtendimento);

		return pAtendimento;
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listar(){
		return entityManager.createQuery("FROM Atendimento dbAtendimento ")
							.getResultList();
	}

	public void excluir(Atendimento pAtendimento){
		pAtendimento = entityManager.merge(pAtendimento);
		entityManager.remove(pAtendimento);
	}
}
