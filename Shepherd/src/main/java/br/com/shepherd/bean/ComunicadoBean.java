package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.shepherd.entity.Comunicado;
import br.com.shepherd.service.ComunicadoService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@SessionScoped
public class ComunicadoBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	private ComunicadoService	comunicadoService;

	private Comunicado			comunicado;

	private List<Comunicado>	listaFiltrada;

	public ComunicadoBean(){
		comunicado = new Comunicado();
	}

	public void cadastrar(){
		try{
			comunicadoService.cadastrar(comunicado);
			comunicado = new Comunicado();

			JSFUtil.addInfoMessage("Comunicado registrado com sucesso!");
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}
	}

	public void alterar(Comunicado pComunicado){
		comunicadoService.alterar(pComunicado);

		JSFUtil.addInfoMessage("Comunicado alterado com sucesso.");
	}

	public List<Comunicado> listar(){
		return comunicadoService.listar();
	}

	public void excluir(Comunicado pComunicado){
		comunicadoService.excluir(pComunicado);

		JSFUtil.addInfoMessage("Comunicado excuído com sucesso.");
	}

	public Comunicado getComunicado(){
		return comunicado;
	}

	public void setComunicado(Comunicado pComunicado){
		comunicado = pComunicado;
	}

	public List<Comunicado> getListaFiltrada(){
		return listaFiltrada;
	}

	public void setListaFiltrada(List<Comunicado> pListaFiltrada){
		listaFiltrada = pListaFiltrada;
	}
}
