package br.com.shepherd.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Sede;

/**
 * Realiza serviços diversos para a entidade Sede.
 *
 * @author Israel Oliveira Santos
 *
 * @methods cadastrar, alterar, excluir
 */
@Stateless
public class SedeService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

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

		Sede existente = null;

		// TODO: Resolver: Assembleias Gerais, Correspondências

		// Consistir dados
		if(null == pSede.getNome() || pSede.getNome().equals("")){
			// Campo Nome obrigatório
			throw new Exception("O cadastro possui campos obrigatórios não preenchidos!");
		}

		if(null == pSede.getEmails() || pSede.getEmails().isEmpty()){
			// Campo Email obrigatório
			throw new Exception("E-mail! Cadastre pelo menos um endereço");
		}

		// Atribuir valores padrão
		if(null == pSede.getCnpj() || pSede.getCnpj().equals("")){
			pSede.setCnpj(null);
		} else{
			existente = buscaCriterio("Sede", "cnpj", pSede.getCnpj());

			if(existente != null){
				// Validar chave única para CNPJ
				throw new Exception("O CNPJ “" + pSede.getCnpj() + "” já existe no cadastro!");
			}
		}

		if(null == pSede.getDataFundacao() || pSede.getDataFundacao().toString().equals("")){
			pSede.setDataFundacao(null);
		}

		if(null == pSede.getCelulas() || pSede.getCelulas().toString().equals("")){
			pSede.setCelulas(null);
		}

		// if(null == pSede.getPresidente() ||
		// pSede.getPresidente().toString().equals("")){
		// pSede.setPresidente(null);
		// }

		if(null == pSede.getPaginaWeb() || pSede.getPaginaWeb().equals("")){
			pSede.setPaginaWeb(null);
		}

		if(null == pSede.getPerfilRedeSocial() || pSede.getPerfilRedeSocial().equals("")){
			pSede.setPerfilRedeSocial(null);
		}

		if(pSede.isMae()){
			pSede.setSedeMae(null);
		} else{
			if(null == pSede.getSedeMae() || pSede.getSedeMae().toString().equals("")){
				// Sede filha não possui Sede Mãe
				throw new Exception("Nenhuma Sede-Mãe selecionada! Obrigatório para Sede-Filha!");
			}
		}

		pSede.setAtiva(true);

		if(null == pSede.getEndereco().getCep() || pSede.getEndereco().getCep().equals("")){
			if(pSede.getEndereco().isGps()){
				if(null == pSede.getEndereco().getLogradouro()
					|| pSede.getEndereco().getLogradouro().equals("")){
					// Logradouro é campo obrigatório, caso seja endereço de GPS
					throw new Exception("Coordenadas de GPS: Campo obrigatório!");
				}
			} else{
				pSede.getEndereco().setCep(null);

				if(null == pSede.getEndereco().getLogradouro()
					|| pSede.getEndereco().getLogradouro().equals("")){
					pSede.getEndereco().setLogradouro(null);
				}

				if(null == pSede.getEndereco().getNumero()
					|| pSede.getEndereco().getNumero().equals("")){
					pSede.getEndereco().setNumero(null);
				}

				if(null == pSede.getEndereco().getComplemento()
					|| pSede.getEndereco().getComplemento().equals("")){
					pSede.getEndereco().setComplemento(null);
				}

				if(null == pSede.getEndereco().getBairro()
					|| pSede.getEndereco().getBairro().equals("")){
					pSede.getEndereco().setBairro(null);
				}

				if(null == pSede.getEndereco().getCidade()
					|| pSede.getEndereco().getCidade().equals("")){
					pSede.getEndereco().setCidade(null);
				}

				if(null == pSede.getEndereco().getEstado()
					|| pSede.getEndereco().getEstado().equals("")){
					pSede.getEndereco().setEstado(null);
				}

				if(null == pSede.getEndereco().getPais()
					|| pSede.getEndereco().getPais().equals("")){
					pSede.getEndereco().setPais(null);
				}
			}
		} else{
			if(null == pSede.getEndereco().getLogradouro()
				|| pSede.getEndereco().getLogradouro().equals("")){
				// Logradouro é campo obrigatório, caso haja CEP
				throw new Exception("Endereço: Campo obrigatório quando há CEP!");
			}

			if(null == pSede.getEndereco().getNumero()
				|| pSede.getEndereco().getNumero().equals("")){
				// Número é campo obrigatório, caso haja CEP
				throw new Exception("Número: Campo obrigatório quando há CEP!");
			}

			if(null == pSede.getEndereco().getComplemento()
				|| pSede.getEndereco().getComplemento().equals("")){
				// Complemento não é campo obrigatório, caso haja CEP
				pSede.getEndereco().setComplemento(null);
			}
			if(null == pSede.getEndereco().getCidade()
				|| pSede.getEndereco().getCidade().equals("")){
				// Cidade é campo obrigatório, caso haja CEP
				throw new Exception("Cidade: Campo obrigatório quando há CEP!");
			}
		}

		// Verificar se a sede já existe
		existente = buscaCriterio("Sede", "nome", pSede.getNome(), "cnpj", pSede.getCnpj());

		if(existente == null){
			entityManager.persist(pSede);

			return pSede;
		} else{
			throw new Exception("Sede “"+ pSede.getNome()
								+ (null != pSede.getCnpj()	? "” com o CNPJ “" + pSede.getCnpj()
															: "")
								+ "” já está cadastrada!");
		}

	}

	public Sede alterar(Sede pSede){
		entityManager.merge(pSede);

		return pSede;
	}

	@SuppressWarnings("unchecked")
	public List<Sede> listar(){
		return entityManager.createQuery("FROM Sede dbSede " + "ORDER BY dbSede.nome")
							.getResultList();
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
}
