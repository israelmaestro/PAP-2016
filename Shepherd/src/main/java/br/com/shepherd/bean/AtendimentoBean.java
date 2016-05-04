package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.shepherd.entity.Atendimento;
import br.com.shepherd.service.AtendimentoService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@RequestScoped
public class AtendimentoBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	private AtendimentoService	atendimentoService;

	private Atendimento			atendimento;

	public AtendimentoBean(){
		atendimento = new Atendimento();
	}

	public void cadastrar(){
		try{
			atendimentoService.cadastrar(atendimento);
			atendimento = new Atendimento();

			JSFUtil.addInfoMessage("Atendimento cadastrado com sucesso!");
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}
	}

	public void alterar(Atendimento pAtendimento){
		atendimentoService.alterar(pAtendimento);

		JSFUtil.addInfoMessage("Atendimento alterado com sucesso.");
	}

	public List<Atendimento> listar(){
		return atendimentoService.listar();
	}

	public void excluir(Atendimento pAtendimento){
		atendimentoService.excluir(pAtendimento);

		JSFUtil.addInfoMessage("Atendimento excuído com sucesso.");
	}

	public Atendimento getAtendimento(){
		return atendimento;
	}

	public void setAtendimento(Atendimento pAtendimento){
		atendimento = pAtendimento;
	}
}
