
package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import br.com.shepherd.entity.Sede;
import br.com.shepherd.service.SedeService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@RequestScoped
public class SedeBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{sedeService}")
	private SedeService			sedeService;

	private Sede				sede;

	public SedeBean(){
		sede = new Sede();
	}

	public String cadastrar(){
		try{
			sedeService.cadastrar(sede);

			JSFUtil.addInfoMessage("Sede “" + sede.getNome() + "” cadastrada com sucesso!");

			sede = new Sede();

		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}

		return "sedeCadastrar";
	}

	public void alterar(Sede pSede){
		sedeService.alterar(pSede);

		JSFUtil.addInfoMessage("Sede alterada com sucesso.");
	}

	public List<Sede> listar(){
		return sedeService.listar();
	}

	public void excluir(Sede pSede){
		sedeService.excluir(pSede);

		JSFUtil.addInfoMessage("Sede excuída com sucesso.");
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}
}
