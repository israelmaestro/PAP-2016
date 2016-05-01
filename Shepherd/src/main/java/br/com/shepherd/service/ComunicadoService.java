package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.shepherd.entity.Comunicado;

@Stateless
public class ComunicadoService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public ComunicadoService(){
	}

	public Comunicado cadastrar(Comunicado pComunicado) throws Exception{

		try{
			entityManager.persist(pComunicado);

			return pComunicado;
		} catch(Exception e){
			throw new Exception("Erro ao cadastrar Comunicado!");
		}

	}

	public Comunicado alterar(Comunicado pComunicado){
		entityManager.merge(pComunicado);
		return pComunicado;
	}

	@SuppressWarnings("unchecked")
	public List<Comunicado> listar(){
		return entityManager.createQuery("FROM Comunicado dbComunicado "
											+ "ORDER BY dbComunicado.nome")
							.getResultList();
	}

	public void excluir(Comunicado pComunicado){
		pComunicado = entityManager.merge(pComunicado);
		entityManager.remove(pComunicado);
	}
}
