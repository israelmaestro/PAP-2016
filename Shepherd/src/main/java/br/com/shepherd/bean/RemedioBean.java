package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.shepherd.entity.Remedio;
import br.com.shepherd.service.RemedioService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@RequestScoped
public class RemedioBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	private RemedioService		remedioService;

	private Remedio				remedio;

	public RemedioBean(){
		remedio = new Remedio();
	}

	public String cadastrar(){
		try{
			remedioService.cadastrar(remedio);
			JSFUtil.addInfoMessage("Remédio “"+ remedio.getNome()
									+ " (" + remedio.getPrincipioAtivo()
									+ ")” cadastrado com sucesso!");

			remedio = new Remedio();
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}

		return "remedioListar";
	}

	public void alterar(Remedio pRemedio){
		remedioService.alterar(pRemedio);

		JSFUtil.addInfoMessage("Remédio alterado com sucesso.");
	}

	public List<Remedio> listar(){
		return remedioService.listar();
	}

	public void excluir(Remedio pRemedio){
		remedioService.excluir(pRemedio);

		JSFUtil.addInfoMessage("Remédio excuído com sucesso.");
	}

	public Remedio getRemedio(){
		return remedio;
	}

	public void setRemedio(Remedio pRemedio){
		remedio = pRemedio;
	}
}
