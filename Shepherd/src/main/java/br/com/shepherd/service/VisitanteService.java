package br.com.shepherd.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.shepherd.entity.Pessoa;

@Stateless
public class VisitanteService{

	@PersistenceContext(name = "ShepherdDB")
	private EntityManager entityManager;

	public VisitanteService(){

	}

	// public Pessoa cadastrar(Pessoa pPessoa) throws Exception{
	//
	// pPessoa.setNome(pPessoa.getNome().trim());
	// pPessoa.setSobrenome(pPessoa.getSobrenome().trim());
	//
	// if(null != pPessoa.getCpf()){
	// if(!pPessoa.getCpf().equals("")){
	// pPessoa.setCpf(pPessoa.getCpf().trim());
	// } else{
	// pPessoa.setCpf(null);
	//
	// System.out.println(JSFUtil.actualTimeStamp() + "Não há CPF...");
	// }
	// }
	//
	// if(null != pPessoa.getRg()){
	// if(!pPessoa.getRg().equals("")){
	// pPessoa.setRg(pPessoa.getRg().trim());
	// } else{
	// pPessoa.setRg(null);
	//
	// System.out.println(JSFUtil.actualTimeStamp() + "Não há RG...");
	// }
	// }
	//
	// pPessoa.setCep(pPessoa.getCep().trim());
	// pPessoa.setLogradouro(pPessoa.getLogradouro().trim());
	// pPessoa.setComplemento(pPessoa.getComplemento().trim());
	// pPessoa.setBairro(pPessoa.getBairro().trim());
	// pPessoa.setCidade(pPessoa.getCidade().trim());
	// pPessoa.setEstado(pPessoa.getEstado().trim().toUpperCase());
	//
	// pPessoa.setTelefoneDdi1(pPessoa.getTelefoneDdi1().trim());
	// pPessoa.setTelefoneNumero1(pPessoa.getTelefoneNumero1().trim());
	// pPessoa.setTelefoneTipo1(pPessoa.getTelefoneTipo1().trim());
	//
	// pPessoa.setTelefoneDdi2(pPessoa.getTelefoneDdi2().trim());
	// pPessoa.setTelefoneNumero2(pPessoa.getTelefoneNumero2().trim());
	// pPessoa.setTelefoneTipo2(pPessoa.getTelefoneTipo2().trim());
	//
	// pPessoa.setTelefoneDdi3(pPessoa.getTelefoneDdi3().trim());
	// pPessoa.setTelefoneNumero3(pPessoa.getTelefoneNumero3().trim());
	// pPessoa.setTelefoneTipo3(pPessoa.getTelefoneTipo3().trim());
	//
	// pPessoa.setEmail1(pPessoa.getEmail1().trim());
	// pPessoa.setEmail2(pPessoa.getEmail2().trim());
	//
	// // Setando Visitante
	// System.out.println(JSFUtil.actualTimeStamp() + "Setando Visitante...");
	// pPessoa.setMember(true);
	// pPessoa.setVisitante(null);
	//
	// // Ativando Visitante
	// System.out.println(JSFUtil.actualTimeStamp() + "Ativando Visitante...");
	// pPessoa.setAtivo(true);
	//
	// // Verificando dados do visitante
	// System.out.println(JSFUtil.actualTimeStamp() + "Verificando dados do
	// visitante...");
	//
	// // Verificando Pessoa existente
	// System.out.println(JSFUtil.actualTimeStamp() + "Verificando
	// pré-existência de visitante");
	//
	// Pessoa existente = buscaPessoaUnica(pPessoa.getNome(),
	// pPessoa.getSobrenome(),
	// pPessoa.getDataNasc(),
	// pPessoa.getRg(),
	// pPessoa.getCpf(),
	// pPessoa.getSexo());
	//
	// if(null == existente){
	// existente = buscaCriterioStr("Pessoa", "cpf", pPessoa.getCpf());
	//
	// System.out.println(JSFUtil.actualTimeStamp() + "Verificando CPF
	// existente");
	//
	// if(null != existente){
	// throw new Exception(") O CPF “"+ pPessoa.getCpf()
	// + "” já existe no cadastro!");
	// } else{
	// existente = buscaCriterioStr("Pessoa", "rg", pPessoa.getRg());
	//
	// System.out.println(JSFUtil.actualTimeStamp() + "Verificando RG
	// existente");
	//
	// if(null != existente){
	// throw new Exception(") O RG “"+ pPessoa.getRg()
	// + "” já existe no cadastro!");
	// } else{
	// System.out.println(JSFUtil.actualTimeStamp() + "Gravando visitante na
	// base");
	//
	// entityManager.persist(pPessoa);
	// return pPessoa;
	// }
	// }
	//
	// } else{
	// throw new Exception(") A pessoa “"+ pPessoa.getNome() + " " +
	// pPessoa.getSobrenome()
	// + (null != pPessoa.getDataNasc() ? "”, nascida em “"
	// + JSFUtil.dataNormal(pPessoa.getDataNasc())
	// : "")
	// + (null != pPessoa.getRg() ? "”, do RG “" + pPessoa.getRg()
	// : "")
	// + (null != pPessoa.getCpf() ? "”, do CPF “"
	// : "")
	// + "” e de sexo " + (pPessoa.getSexo() ? "masculino"
	// : "feminino")
	// + " já existe no cadastro!");
	// }
	//
	// // TODO: Criar regras para "MERGE"
	//
	// }

	public Pessoa alterar(Pessoa pResponsavel){
		entityManager.merge(pResponsavel);

		return pResponsavel;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listar(){
		return entityManager.createQuery("FROM Pessoa dbPessoa "
											+ "WHERE dbPessoa.isVisitor = true "
											+ "ORDER BY dbPessoa.nome")
							.getResultList();
	}

	public void excluir(Pessoa pResponsavel){
		pResponsavel = entityManager.merge(pResponsavel);
		entityManager.remove(pResponsavel);
	}

	public Pessoa buscaPessoaUnica(	String pNome,
									String pSobrenome,
									Date pDataNasc,
									String pRg,
									String pCpf,
									boolean pSexo){
		Query query = entityManager.createQuery("FROM Pessoa dbPessoa "
												+ "WHERE UPPER(dbPessoa.nome) = UPPER(:p1) "
												+ "AND UPPER(dbPessoa.sobrenome) = UPPER(:p2) "
												+ (null == pDataNasc	? "AND dbPessoa.dataNasc IS NULL "
																		: "AND dbPessoa.dataNasc = :p3 ")
												+ (null == pRg	? "AND dbPessoa.rg IS NULL "
																: "AND dbPessoa.rg = :p4 ")
												+ (null == pCpf	? "AND dbPessoa.cpf IS NULL "
																: "AND dbPessoa.cpf = :p5 ")
												+ "AND UPPER(dbPessoa.sexo) = UPPER(:p6) ");
		query.setParameter("p1", pNome);
		query.setParameter("p2", pSobrenome);

		if(null != pDataNasc){
			query.setParameter("p3", pDataNasc);
		}

		if(null != pRg){
			query.setParameter("p4", pRg);
		}

		if(null != pCpf){
			query.setParameter("p5", pCpf);
		}

		query.setParameter("p6", pSexo);

		try{
			return (Pessoa) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}

	public Pessoa buscaCriterioStr(String pTabela, String pCampo, String pValor){
		Query query = entityManager.createQuery("FROM "+ pTabela + " db" + pTabela + " "
												+ "WHERE "
												+ (null == pValor	? "db"+ pTabela + "." + pCampo
																	+ " is null "
																	: "db"+ pTabela + "." + pCampo
																		+ " = :p1"));
		if(null != pValor){
			query.setParameter("p1", pValor);
		}

		try{
			return (Pessoa) query.getSingleResult();
		} catch(NoResultException n){
			return null;
		}
	}
}
