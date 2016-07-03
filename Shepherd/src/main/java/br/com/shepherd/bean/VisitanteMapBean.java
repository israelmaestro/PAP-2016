package br.com.shepherd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.shepherd.entity.PessoaCelula;
import br.com.shepherd.entity.PessoaSede;
import br.com.shepherd.entity.Sede;
import br.com.shepherd.service.SedeService;
import br.com.shepherd.service.VisitanteService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@RequestScoped
public class VisitanteMapBean implements Serializable{
	private static final long	serialVersionUID	= 2012678880138355942L;

	@EJB
	private VisitanteService	visitanteService;

	@EJB
	private SedeService			sedeService;

	private MapModel			mapModel;

	private List<PessoaCelula>	visitantesCelulas	= new ArrayList<PessoaCelula>();
	private List<PessoaSede>	visitantesSedes		= new ArrayList<PessoaSede>();
	private List<Sede>			sedes				= new ArrayList<Sede>();

	Gmap						gmap				= new Gmap();

	public VisitanteMapBean(){

	}

	@PostConstruct
	public void postConstruct(){
		mapModel = new DefaultMapModel();
		visitantesCelulas = visitanteService.listarVisitantesCelulas();
		visitantesSedes = visitanteService.listarVisitantesSedes();
		sedes = sedeService.listar();
		LatLng latLng;

		for(PessoaCelula visitanteCelula : visitantesCelulas){
			if(null != visitanteCelula.getPessoa().getEndereco()){
				latLng = gmap.converterCoordenadas(visitanteCelula	.getPessoa().getEndereco()
																		.getCoordenadas());
				try{
					mapModel.addOverlay(new Marker(	latLng,
													visitanteCelula.getPessoa().getNome()
															+ " "
															+ visitanteCelula	.getPessoa()
																			.getSobrenome(),
													visitanteCelula.getPessoa().getNome()			+ " "
																									+ visitanteCelula	.getPessoa()
																														.getSobrenome(),
													"/Shepherd" + JSFUtil.getProperty("mapaVisitanteCelula")));
				} catch(IOException e){
					// Nada a fazer
				}
			}
		}

		for(PessoaSede visitanteSede : visitantesSedes){
			if(null != visitanteSede.getPessoa().getEndereco()){
				latLng = gmap.converterCoordenadas(visitanteSede	.getPessoa().getEndereco()
																	.getCoordenadas());
				try{
					mapModel.addOverlay(new Marker(	latLng,
													visitanteSede.getPessoa().getNome()+ " "
															+ visitanteSede	.getPessoa()
																				.getSobrenome(),
													visitanteSede.getPessoa().getNome()			+ " "
																									+ visitanteSede	.getPessoa()
																														.getSobrenome(),
													"/Shepherd" + JSFUtil.getProperty("mapaVisitanteSede")));
				} catch(IOException e){
					// Nada a fazer
				}
			}
		}

		for(Sede sede : sedes){
			if(null != sede.getEndereco()){
				latLng = gmap.converterCoordenadas(sede.getEndereco().getCoordenadas());

				try{
					mapModel.addOverlay(new Marker(	latLng,
													JSFUtil.getProperty("convencao")+ " "
															+ sede.getNome()
															+ (sede.isMae() ? " (mãe)" : ""),
													sede.getNome() + (sede.isMae() ? " (mãe)" : ""),
													"/Shepherd" + JSFUtil.getProperty(sede.isMae()	? "mapaSedeMae"
																									: "mapaSede")));
				} catch(Exception e){
					// Nada a fazer
				}
			}
		}
	}

	public MapModel getMapModel(){
		return mapModel;
	}

	public void setMapModel(MapModel pMapModel){
		mapModel = pMapModel;
	}

}
