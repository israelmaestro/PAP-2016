package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Celula;
import br.com.shepherd.entity.Sede;

/**
 * Realiza serviços diversos para a entidade Celula.
 *
 * @author Israel Oliveira Santos
 *
 * @methods cadastrar, alterar, excluir
 */
@Stateless
public class CelulaService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public CelulaService(){
	}

	public Celula cadastrar(Celula pCelula) throws Exception{

		Celula existente = null;

		// Consistindo dados
		if(null == pCelula.getNome() || pCelula.getNome().equals("")){
			// Campo Nome obrigatório
			throw new Exception("O cadastro possui campos obrigatórios não preenchidos!");
		}

		// Atribuir valores padrão
		pCelula.setActive(true);

		if(null != pCelula.getComentarios() || pCelula.getComentarios().equals("")){
			// Tornar nulo
			pCelula.setComentarios(null);
		}

		// Consistir endereço
		if(null == pCelula.getEnderecoCep() || pCelula.getEnderecoCep().equals("")){
			if(pCelula.isGpsAddress()){
				if(null == pCelula.getEnderecoLogradouro() || pCelula.getEnderecoLogradouro().equals("")){
					// Logradouro é campo obrigatório, caso seja endereço de GPS
					throw new Exception("Coordenadas de GPS: Campo obrigatório!");
				}
			} else{
				pCelula.setEnderecoCep(null);

				if(null == pCelula.getEnderecoLogradouro() || pCelula.getEnderecoLogradouro().equals("")){
					pCelula.setEnderecoLogradouro(null);
				}

				if(null == pCelula.getEnderecoNumero() || pCelula.getEnderecoNumero().equals("")){
					pCelula.setEnderecoNumero(null);
				}

				if(null == pCelula.getEnderecoComplemento() || pCelula.getEnderecoComplemento().equals("")){
					pCelula.setEnderecoComplemento(null);
				}

				if(null == pCelula.getEnderecoBairro() || pCelula.getEnderecoBairro().equals("")){
					pCelula.setEnderecoBairro(null);
				}

				if(null == pCelula.getEnderecoCidade() || pCelula.getEnderecoCidade().equals("")){
					pCelula.setEnderecoCidade(null);
				}

				if(null == pCelula.getEnderecoEstado() || pCelula.getEnderecoEstado().equals("")){
					pCelula.setEnderecoEstado(null);
				}

				if(null == pCelula.getEnderecoPais() || pCelula.getEnderecoPais().equals("")){
					pCelula.setEnderecoPais(null);
				}
			}
		} else{
			if(null == pCelula.getEnderecoLogradouro() || pCelula.getEnderecoLogradouro().equals("")){
				// Logradouro é campo obrigatório, caso haja CEP
				throw new Exception("Endereço: Campo obrigatório quando há CEP!");
			}

			if(null == pCelula.getEnderecoNumero() || pCelula.getEnderecoNumero().equals("")){
				// Logradouro é campo obrigatório, caso haja CEP
				throw new Exception("Número: Campo obrigatório quando há CEP!");
			}

			if(null == pCelula.getEnderecoCidade() || pCelula.getEnderecoCidade().equals("")){
				// Logradouro é campo obrigatório, caso haja CEP
				throw new Exception("Cidade: Campo obrigatório quando há CEP!");
			}
		}

		// Validando chave única
		// existente = buscaCriterio("Celula", "nome", pCelula.getNome(),
		// "sede",
		// pCelula.getSede());
		//
		// if(existente == null){
		entityManager.persist(pCelula);
		//
		return pCelula;
		// } else{
		// throw new Exception("Celula “"+ pCelula.getNome()
		// + (pCelula.getSede() == null ? ""
		// : "” na sede “" + pCelula.getSede())
		// + "” já está cadastrada! Escolha outro nome, ou outra sede.");
		// }
	}

	public Celula alterar(Celula pCelula){
		entityManager.merge(pCelula);

		return pCelula;
	}

	@SuppressWarnings("unchecked")
	public List<Celula> listar(){
		return entityManager.createQuery("FROM Celula dbCelula " + "ORDER BY dbCelula.nome")
							.getResultList();
	}

	public void excluir(Celula pCelula){
		pCelula = entityManager.merge(pCelula);
		entityManager.remove(pCelula);
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
	public Celula buscaCriterio(String pTabela, String pCampo, String pValor){
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
			return (Celula) query.getSingleResult();
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
	public Celula buscaCriterio(String pTabela, String pCampo1, String pValor1, String pCampo2,
								Sede pValor2){
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
			return (Celula) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
