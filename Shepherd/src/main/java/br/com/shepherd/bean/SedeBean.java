
package br.com.shepherd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import br.com.shepherd.entity.Email;
import br.com.shepherd.entity.Sede;
import br.com.shepherd.entity.Telefone;
import br.com.shepherd.service.SedeService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@SessionScoped
public class SedeBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;


	// Persistência
	@EJB
	@ManagedProperty("#{sedeService}")
	private SedeService			sedeService;

	private Sede				sede;

	private Sede				sedeTemp;

	public SedeBean(){
		sede = new Sede();
	}

	public void botaoRemoverTelefone(Telefone pTelefone){
		sede.removeTelefone(pTelefone);
	}

	public void botaoAdicionarTelefone(AjaxBehaviorEvent pAjax){
		sede.addTelefone(new Telefone());
	}

	public void botaoRemoverEmail(Email pEmail){
		sede.removeEmail(pEmail);
	}

	public void botaoAdicionarEmail(AjaxBehaviorEvent pAjax){
		sede.addEmail(new Email());
	}

	public String cadastrar(){
		try{
			// sede.setTelefones(tels);
			sedeService.cadastrar(sede);

			JSFUtil.addInfoMessage("Sede “" + sede.getNome() + "” cadastrada com sucesso!");

			sede = new Sede();

		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}

		return "sedeCadastrar";
	}

	public String prepararAlteracao(Sede pSede){

		setSedeTemp(pSede);

		return "sedeAlterar";
	}

	public void alterar(Sede pSede){
		sedeService.alterar(pSede);

		JSFUtil.addInfoMessage("Sede alterada com sucesso.");
	}

	public void limpar(){
		setSede(new Sede());
	}

	public List<Sede> listar(){
		return sedeService.listar();
	}

	public List<Sede> listarMaes(){
		return sedeService.listarMaes();
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

	public Sede getSedeTemp(){
		return sedeTemp;
	}

	public void setSedeTemp(Sede pSedeTemp){
		sedeTemp = pSedeTemp;
	}

	public String getProperty(String pKey){
		try{
			return JSFUtil.getProperty(pKey);
		} catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}

	// TODO: Implementar setProperty
}
