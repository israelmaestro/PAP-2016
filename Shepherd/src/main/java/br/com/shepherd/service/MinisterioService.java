package br.com.shepherd.service;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Ministerio;
import br.com.shepherd.service.util.JSFUtil;;

@Stateless
public class MinisterioService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;
	private Ministerio		ministerio;
	private Ministerio ministerioTemp;

	public MinisterioService(){
	}

	/**
	 * Lista os 5 ministérios e, caso não existam ainda, são criados conforme o
	 * arquivo de propriedades
	 *
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Ministerio> listar() throws IOException{
		for(int i = 0; i < 5; i++){
			ministerio = new Ministerio();
			ministerioTemp = new Ministerio();

			String tKey = "ministerio_" + i;
			String tMinisterioNome = JSFUtil.getProperty(tKey);
			ministerio.setNome(tMinisterioNome);

			ministerioTemp = buscaNome(tMinisterioNome);

			if(null == ministerioTemp || ministerioTemp.equals("")){
				try{
					entityManager.persist(ministerio);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		return entityManager.createQuery("FROM Ministerio dbMinisterio "
											+ "ORDER BY dbMinisterio.nome")
							.getResultList();
	}

	public Ministerio buscaNome(String pNome){
		Query query = entityManager.createQuery("FROM Ministerio dbMinisterio "
												+ "WHERE UPPER(dbMinisterio.nome) = UPPER(:p1)");
		query.setParameter("p1", pNome);
		try{
			return (Ministerio) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
