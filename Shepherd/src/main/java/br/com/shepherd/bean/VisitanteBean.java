package br.com.shepherd.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import br.com.shepherd.service.VisitanteService;

@Named
@RequestScoped
public class VisitanteBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{visitanteService}")
	private VisitanteService	visitanteService;

}
