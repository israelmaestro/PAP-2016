
package br.com.shepherd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import br.com.shepherd.entity.Celula;
import br.com.shepherd.entity.PessoaCelula;
import br.com.shepherd.service.CelulaService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@SessionScoped
public class CelulaBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{celulaService}")
	private CelulaService		celulaService;

	private Celula				celula;

	private Celula 				celulaTemp;

	public CelulaBean(){
		celula = new Celula();
	}

	public String cadastrar(){
		try{
			celulaService.cadastrar(celula);

			JSFUtil.addInfoMessage("Celula “" + celula.getNome() + "” cadastrada com sucesso!");

			celula = new Celula();

			return "celulaListar";
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
			return "celulaCadastrar";
		}

	}

	public String prepararAlteracao(Celula pCelula){

		setCelulaTemp(pCelula);

		return "celulaAlterar";
	}

	public void alterar(Celula pCelula){
		celulaService.alterar(pCelula);

		JSFUtil.addInfoMessage("Celula alterada com sucesso.");
	}

	public List<Celula> listar(){
		return celulaService.listar();
	}

	public void excluir(Celula pCelula){
		celulaService.excluir(pCelula);

		JSFUtil.addInfoMessage("Célula excuída com sucesso.");
	}

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
	}

	public Celula getCelulaTemp() {
		return celulaTemp;
	}

	public void setCelulaTemp(Celula pCelulaTemp) {
		celulaTemp = pCelulaTemp;
	}

	public void botaoRemoverPessoa(PessoaCelula pPessoaCelula){
		celula.removePessoa(pPessoaCelula);
	}

	public void botaoAdicionarPessoa(AjaxBehaviorEvent pAjax){
		celula.addPessoa(new PessoaCelula());
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
