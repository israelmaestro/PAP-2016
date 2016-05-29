package br.com.shepherd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.shepherd.entity.Ministerio;
import br.com.shepherd.service.MinisterioService;

@Named
@RequestScoped
public class MinisterioBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	private MinisterioService	ministerioService;

	private Ministerio			ministerio;

	public MinisterioBean(){
		setMinisterio(new Ministerio());
	}

	public List<Ministerio> listar() throws IOException{
		return ministerioService.listar();
	}

	public Ministerio getMinisterio(){
		return ministerio;
	}

	public void setMinisterio(Ministerio pMinisterio){
		ministerio = pMinisterio;
	}

}
