package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Perfil;
import br.com.shepherd.enums.LiberacaoAcesso;

@Stateless
public class PerfilService{
	/*
	 * Essa anotação define o objeto que gerencia as entidades com o banco de
	 * dados configurado no persistence.xml
	 */
	@PersistenceContext(unitName = "ShepherdDB")
	private EntityManager entityManager;

	public Perfil cadastrar(Perfil pPerfil) throws Exception{

		Perfil existente = buscaAcesso(pPerfil.getAcesso());

		if(existente == null){
			entityManager.persist(pPerfil);

			return pPerfil;
		} else{
			throw new Exception("Perfil '"+ pPerfil.getAcesso()
								+ "' já está cadastrado!");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Perfil> listar(){
		return entityManager.createQuery("FROM Perfil dbPerfil")
							.getResultList();
	}

	public Perfil alterar(Perfil pPerfil){
		entityManager.merge(pPerfil);

		return pPerfil;
	}

	public void excluir(Perfil pPerfil){
		pPerfil = entityManager.merge(pPerfil);
		entityManager.remove(pPerfil);
	}

	public Perfil buscaAcesso(LiberacaoAcesso pLiberacaoAcesso){
		Query query = entityManager.createQuery("FROM Perfil dbPerfil WHERE UPPER(dbPerfil.acesso) = UPPER(:p1)");
		query.setParameter("p1", pLiberacaoAcesso);
		try{
			return (Perfil) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
