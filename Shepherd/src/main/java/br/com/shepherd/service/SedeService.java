package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import br.com.shepherd.entity.Sede;
import br.com.shepherd.service.util.SedeUtils;

/**
 * Realiza serviços diversos para a entidade Sede.
 *
 * @author Israel Oliveira Santos
 * @methods cadastrar, alterar, excluir
 */
@Stateless
public class SedeService{
	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	SedeUtils				sedeUtils = new SedeUtils();

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
		pSede.setAtiva(true);

		Sede existente = null;

		// TODO: Resolver: Assembleias Gerais, Correspondências

		pSede = sedeUtils.consistir(pSede);

		// Verificando chave única para CNPJ
		if(null != pSede.getCnpj()){
			existente = buscaCriterio("Sede", "cnpj", pSede.getCnpj());

			if(existente != null){
				// Validar chave única para CNPJ
				throw new Exception("O CNPJ “" + pSede.getCnpj() + "” já existe no cadastro!");
			}
		} else{
			pSede.setCnpj(null);
		}

		// Verificar se há mais de uma sede-mãe na mesma cidade
		if(pSede.isMae()
			&& !isUnicaMae(pSede.getEndereco()
								.getCidade())){
			throw new Exception("Não pode haver mais de uma sede-mãe na mesma cidade!");
		}


		// Verificar se a sede já existe
		existente = buscaCriterio("Sede", "nome", pSede.getNome(), "cnpj", pSede.getCnpj());

		if(null == existente){
			entityManager.persist(pSede);

			return pSede;
		} else{
			throw new Exception("Sede “"+ pSede.getNome()
								+ (null != pSede.getCnpj()	? "” com o CNPJ “" + pSede.getCnpj()
															: "")
								+ "” já está cadastrada!");
		}

	}

	public Sede alterar(Sede pSede) throws Exception{
		pSede = sedeUtils.consistir(pSede);

		Sede existente = null;

		// TODO: Resolver: Assembleias Gerais, Correspondências

		pSede = sedeUtils.consistir(pSede);

		// Verificando chave única para CNPJ
		if(null != pSede.getCnpj()){
			existente = buscaCriterio("Sede", "cnpj", pSede.getCnpj());

			if(existente != null){
				// Validar chave única para CNPJ
				throw new Exception("O CNPJ “" + pSede.getCnpj() + "” já existe no cadastro!");
			}
		} else{
			pSede.setCnpj(null);
		}

		// Verificar se há mais de uma sede-mãe na mesma cidade
		if(pSede.isMae()
			&& !isUnicaMae(pSede.getEndereco()
								.getCidade())){
			throw new Exception("Não pode haver mais de uma sede-mãe na mesma cidade!");
		}


		// Verificar se a sede já existe
		existente = buscaCriterio("Sede", "nome", pSede.getNome(), "cnpj", pSede.getCnpj());

		if(null == existente){
			entityManager.persist(pSede);

			return pSede;
		} else{
			throw new Exception("Sede “"+ pSede.getNome()
								+ (null != pSede.getCnpj()	? "” com o CNPJ “" + pSede.getCnpj()
															: "")
								+ "” já está cadastrada!");
		}
	}

//	@SuppressWarnings("unchecked")
//	public List<Sede> listar(){
//		return entityManager.createQuery("FROM Sede dbSede " + "ORDER BY dbSede.nome")
//							.getResultList();
//	}

	@SuppressWarnings("unchecked")
	public List<Sede> listar(){
		List<Sede> tSedes = entityManager.createQuery("FROM Sede dbSede " + "ORDER BY dbSede.nome")
							.getResultList();
		
		for (Sede pSede : tSedes) {
			Hibernate.initialize(pSede.getCelulas());
			Hibernate.initialize(pSede.getTelefones());
			Hibernate.initialize(pSede.getEmails());
		}		
		return tSedes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sede> listarMaes(){
		return entityManager.createQuery("FROM Sede dbSede"
											+ " WHERE dbSede.mae IS TRUE"
											+ " ORDER BY dbSede.nome")
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
	public Sede buscaCriterio(	String pTabela, String pCampo1, String pValor1, String pCampo2,
								String pValor2){
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

	/**
	 * Verifica se a sede, sendo mãe, é a única na cidade
	 *
	 * @param pCidade
	 * @return true (é unica) / false (não é única)
	 */
	public boolean isUnicaMae(String pCidade){
		boolean tResult = true;

		Query query = entityManager.createQuery("FROM Sede dbSede WHERE dbSede.mae = true "
												+ "AND UPPER(dbSede.endereco.cidade) = UPPER(:p1)");

		query.setParameter("p1", pCidade);

		try{
			Sede tSede = (Sede) query.getSingleResult();

			if(tSede.isMae() && tSede.isAtiva() && tSede.getEndereco().getCidade().equals(pCidade)){
				tResult = false;
			} else{
				tResult = true;
			}
		} catch(NoResultException n){
			tResult = true;
		}

		return tResult;
	}
}
