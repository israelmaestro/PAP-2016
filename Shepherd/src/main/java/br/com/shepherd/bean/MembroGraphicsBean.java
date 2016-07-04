package br.com.shepherd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import br.com.shepherd.entity.Celula;
import br.com.shepherd.service.CelulaService;
import br.com.shepherd.service.MembroService;
import br.com.shepherd.service.util.JSFUtil;

@ManagedBean
public class MembroGraphicsBean implements Serializable{
	private PieChartModel	pieGraphic;

	@EJB
	private CelulaService	celulaService;

	@EJB
	private MembroService	membroService;

	@PostConstruct
	public void init(){
		createPieModels();
	}

	public PieChartModel getGraficoMembrosCelulas(){
		pieGraphic = new PieChartModel();
		List<Celula> celulas = celulaService.listar();

		for(Celula celula : celulas){
			pieGraphic.set(celula.getNome(), membroService.countPessoasCelulas(celula));
		}

		pieGraphic.setTitle("Membros nas Células");
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
