package br.com.shepherd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.shepherd.service.util.JSFUtil;

@Named
@ViewScoped
public class Gmap implements Serializable{
	private static final long	serialVersionUID	= -1534489621250330232L;

	private boolean				acumula				= false;
	private boolean				comMensagem			= false;
	private boolean				centrarMapa			= true;

	/**
	 * Objeto principal do GMap
	 */
	private MapModel			mapModel;

	/**
	 * Atributo de coordenadas para centrar o mapa
	 */
	private LatLng				posicaoCentral;

	private String				posicaoCentralTxt;

	/**
	 * Construtor
	 *
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public Gmap() throws NumberFormatException, IOException{
		mapModel = new DefaultMapModel();
		posicaoCentral = new LatLng(Float.parseFloat(JSFUtil.getProperty("enderecoPrincipalLatitude")),
									Float.parseFloat(JSFUtil.getProperty("enderecoPrincipalLongitude")));
	}

	/**
	 * Recebe as coordenadas do endereço na variável "event"
	 *
	 * @param event
	 */
	public void marcarMapa(boolean pAcumula, boolean pComMensagem, boolean pCentrarMapa){
		acumula = pAcumula;

		comMensagem = pComMensagem;

		centrarMapa = pCentrarMapa;
	}

	public	void

	onGeocode(GeocodeEvent event){
		if(!acumula){
			mapModel = new DefaultMapModel();
		}

		List<GeocodeResult> results = event.getResults();

		if(centrarMapa){
			posicaoCentral = results.get(0).getLatLng();
		}

		mapModel.addOverlay(new Marker(results.get(0).getLatLng()));

		if(comMensagem){
			FacesContext.getCurrentInstance()
						.addMessage(null, new FacesMessage(results.get(0).getLatLng().toString()));
		}
	}

	public boolean isAcumula(){
		return acumula;
	}

	public void setAcumula(boolean pAcumula){
		acumula = pAcumula;
	}

	public boolean isComMensagem(){
		return comMensagem;
	}

	public void setComMensagem(boolean pComMensagem){
		comMensagem = pComMensagem;
	}

	public boolean isCentrarMapa(){
		return centrarMapa;
	}

	public void setCentrarMapa(boolean pCentrarMapa){
		centrarMapa = pCentrarMapa;
	}

	public void setPosicaoCentral(LatLng pPosicaoCentral){
		posicaoCentral = pPosicaoCentral;
	}

	public MapModel getMapModel(){
		return mapModel;
	}

	public void setMapModel(MapModel mapModel){
		this.mapModel = mapModel;
	}

	public String getPosicaoCentral(){
		posicaoCentralTxt = posicaoCentral.getLat() + ", " + posicaoCentral.getLng();
		return posicaoCentralTxt;
	}

	public String getPosicaoCentralTxt(){
		return posicaoCentralTxt;
	}

	public void setPosicaoCentralTxt(String pPosicaoCentralTxt){
		posicaoCentralTxt = pPosicaoCentralTxt;
	}


}
