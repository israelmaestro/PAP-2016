package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.NEspecial;
import br.com.shepherd.service.util.NEspecialUtils;;

@Stateless
public class NEspecialService{
	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	private NEspecialUtils	nEspecialUtils	= new NEspecialUtils();

	public NEspecialService(){
	}

	/**
	 * Realiza o cadastro de uma necessidade especial
	 *
	 * @param pNEspecial
	 * @return pNEspecial
	 * @throws Exception
	 */
	public NEspecial cadastrar(NEspecial pNEspecial) throws Exception{
		pNEspecial = nEspecialUtils.consistir(pNEspecial);

		NEspecial existente = buscaNome(pNEspecial.getNome());

		if(existente == null){
			entityManager.persist(pNEspecial);

			return pNEspecial;
		} else{
			throw new Exception("Necessidade especial “"
								+ pNEspecial.getNome()
								+ "” já está cadastrada!");
		}

	}

	/**
	 * Altera uma necessidade especial existente
	 *
	 * @param pNEspecial
	 * @return pNEspecial
	 * @throws Exception
	 */
	public NEspecial alterar(NEspecial pNEspecial) throws Exception{
		pNEspecial = nEspecialUtils.consistir(pNEspecial);

		NEspecial existente = buscaNome(pNEspecial.getNome());

		if(null != existente){
			// Já existe uma NE com mesmo nome...

			if(existente.getId().equals(pNEspecial.getId())){
				// ... mas é com a mesma ID
				entityManager.merge(pNEspecial);

				return pNEspecial;
			} else{
				throw new Exception("Já existe uma Necessidade Especial com o nome “"
									+ pNEspecial.getNome() + "”.");
			}
		} else{
			// Ainda não existe. Pode gravar...
			entityManager.merge(pNEspecial);

			return pNEspecial;
		}
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
