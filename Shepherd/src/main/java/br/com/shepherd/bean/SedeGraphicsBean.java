package br.com.shepherd.bean;

import javax.faces.bean.ManagedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.primefaces.model.chart.PieChartModel;

import br.com.shepherd.entity.Sede;
import br.com.shepherd.service.MembroService;
import br.com.shepherd.service.SedeService;
import br.com.shepherd.service.util.JSFUtil;

@ManagedBean
public class SedeGraphicsBean implements Serializable{

	private static final long serialVersionUID = 7598106241524756942L;

	private PieChartModel	pieGraphic;
	
	@EJB
	private MembroService	membroService;

	@EJB
	public SedeService 		sedeService;
	
	@PostConstruct
	public void init(){
		createPieModels();
	}
	
	public PieChartModel getGraficoMembrosSedes(){
		pieGraphic = new PieChartModel();
		List<Sede> sedes = sedeService.listar();

		for(Sede sede : sedes){
			pieGraphic.set(sede.getNome(), membroService.countPessoasSedes(sede));
		}

		pieGraphic.setTitle("Membros nas Sedes");
		try{
			pieGraphic.setLegendPosition(JSFUtil.getProperty("anyPosition"));
		} catch(IOException e){
			// Nada a fazer...
		}

		return pieGraphic;
	}

	private void createPieModels(){
		createPieGraphic();
	}

	private void createPieGraphic(){
		pieGraphic = new PieChartModel();

		pieGraphic.set("Brand 1", 540);
		pieGraphic.set("Brand 2", 325);
		pieGraphic.set("Brand 3", 702);
		pieGraphic.set("Brand 4", 421);


		pieGraphic.setLegendPosition("w");
	}
}
