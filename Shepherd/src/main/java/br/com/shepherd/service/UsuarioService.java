package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Usuario;

@Stateless
public class UsuarioService{
	@PersistenceContext(unitName = "ShepherdDB")
	private EntityManager entityManager;

	public Usuario cadastrar(Usuario pUsuario) throws Exception{

		pUsuario.setConta(pUsuario.getConta().trim());
		pUsuario.setSenha(pUsuario.getSenha().trim());

		Usuario existente = buscaConta(pUsuario.getConta());

		if(existente == null){
			entityManager.persist(pUsuario);

			return pUsuario;
		} else{
			throw new Exception("Usuário da conta '"+ pUsuario.getConta()
								+ "' já está cadastrado!");
		}

	}

	public Usuario alterar(Usuario pUsuario){
		entityManager.merge(pUsuario);

		return pUsuario;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar(){
		return entityManager.createQuery("FROM Usuario dbUsuario "
											+ "ORDER BY dbUsuario.conta")
							.getResultList();
	}

	public void excluir(Usuario pUsuario){
		pUsuario = entityManager.merge(pUsuario);
		entityManager.remove(pUsuario);
	}

	public Usuario buscaConta(String pConta){
		Query query = entityManager.createQuery("FROM Usuario dbUsuario WHERE UPPER(dbUsuario.conta) = UPPER(:p1)");
		query.setParameter("p1", pConta);
		try{
			return (Usuario) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
