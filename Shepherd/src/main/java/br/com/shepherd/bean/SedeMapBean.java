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
import br.com.shepherd.entity.Sede;
import br.com.shepherd.service.MembroService;
import br.com.shepherd.service.SedeService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@RequestScoped
public class SedeMapBean implements Serializable{

	private static final long serialVersionUID = -2073616058082795058L;

	@EJB
	private SedeService 		sedeService;
	
	@EJB
	private MembroService		membroService;	
	
	private MapModel			mapModel;
	
	private List<PessoaCelula>	membrosCelulas		= new ArrayList<PessoaCelula>();
	private List<Sede>			sedes = new ArrayList<Sede>();
	
	Gmap	gmap	= new Gmap();
	
	public SedeMapBean() {

	}
	
	@PostConstruct
	public void postConstruct(){
		
		mapModel = new DefaultMapModel();
		membrosCelulas = membroService.listar();
		sedes = sedeService.listar();
		LatLng latLng;
		
		for(PessoaCelula membroCelula : membrosCelulas){
			if(null != membroCelula.getPessoa().getEndereco()){
				latLng = gmap.converterCoordenadas(membroCelula	.getPessoa().getEndereco()
																.getCoordenadas());
				try{
					mapModel.addOverlay(new Marker(	latLng,
													membroCelula.getPessoa().getNome()+ " "
															+ membroCelula	.getPessoa()
																			.getSobrenome(),
													membroCelula.getPessoa().getNome()			+ " "
																								+ membroCelula	.getPessoa()
																												.getSobrenome(),
													"/Shepherd" + JSFUtil.getProperty("mapaMembro")));
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
													sede.getNome()
													+ (sede.isMae() ? " (mãe)" : ""),
													"/Shepherd"
													+ JSFUtil.getProperty(sede.isMae()	? "mapaSedeMae"
																						: "mapaSede")
													));
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
