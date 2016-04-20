
package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import br.com.shepherd.entity.Frente;
import br.com.shepherd.service.FrenteService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@RequestScoped
public class FrenteBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{frenteService}")
	private FrenteService		frenteService;

	private Frente				frente;

	public FrenteBean(){
		frente = new Frente();
	}

	public String cadastrar(){
		try{
			frenteService.cadastrar(frente);

			JSFUtil.addInfoMessage("Frente “" + frente.getNome() + "” cadastrada com sucesso!");

			frente = new Frente();

		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}

		return "frenteCadastrar";
	}

	public void alterar(Frente pFrente){
		frenteService.alterar(pFrente);

		JSFUtil.addInfoMessage("Frente alterada com sucesso.");
	}

	public List<Frente> listar(){
		return frenteService.listar();
	}

	public List<Frente> listarTipoCelula(){
		return frenteService.listarTipoCelula();
	}

	public void excluir(Frente pFrente){
		frenteService.excluir(pFrente);

		JSFUtil.addInfoMessage("Frente excuída com sucesso.");
	}

	public Frente getFrente(){
		return frente;
	}

	public void setFrente(Frente pFrente){
		frente = pFrente;
	}
}
