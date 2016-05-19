package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import br.com.shepherd.entity.Pessoa;
import br.com.shepherd.service.VisitanteService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@RequestScoped
public class VisitanteBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{visitanteService}")
	private VisitanteService	visitanteService;

	private Pessoa				pessoa;

	public VisitanteBean(){
		pessoa = new Pessoa();
	}

	// public String cadastrar(){
	// try{
	// visitanteService.cadastrar(pessoa);
	//
	// JSFUtil.addInfoMessage("Visitante “"+ pessoa.getNome() + " " +
	// pessoa.getSobrenome()
	// + "” cadastrado com sucesso!");
	//
	// pessoa = new Pessoa();
	//
	// return "visitanteListar";
	// } catch(Exception e){
	// JSFUtil.addWarnMessage(e.getMessage());
	// return "visitanteCadastrar";
	// }
	// }

	public void alterar(Pessoa pPessoa){
		visitanteService.alterar(pPessoa);

		JSFUtil.addInfoMessage("Visitante “"+ pessoa.getNome()
								+ " "
								+ pessoa.getSobrenome()
								+ "” alterado com sucesso.");
	}

	public List<Pessoa> listar(){
		return visitanteService.listar();
	}

	public void excluir(Pessoa pPessoa){
		visitanteService.excluir(pPessoa);

		JSFUtil.addInfoMessage("Visitante “"+ pessoa.getNome()
								+ " "
								+ pessoa.getSobrenome()
								+ "” excuído com sucesso.");
	}

	public Pessoa getVisitante(){
		return pessoa;
	}

	public void setVisitante(Pessoa pVisitante){
		pessoa = pVisitante;
	}
}
