package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Ministro implements Serializable{
	private static final long	serialVersionUID	= -6129701255435081024L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Informações gerais
	// TODO: verificar se os "fetchs" não vão dar problema
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Coordenador			coordenador;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataNomeacao;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				datadataEntrega;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataOrdenacao;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataOrdenacaoEspecifica;

	/*
	 * TODO: Resolver o caso do ministro presidente. Ele pode ser presidente de
	 * mais de uma sede? Caso sim, se ele deixa de ser presidente, deixa de ser
	 * de todas?
	 */
	private String[]			ministerios;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataPresidentePosse;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataPresidenteEntrega;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "presidente")
	private List<Sede>			sedes;

	// Flags
	private boolean				isPresident			= false;

	private boolean				isConsagrated		= false;

	private boolean				minEspecifico		= false;

	public Ministro(){
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){ return true; }
		if(obj == null){ return false; }
		if(getClass() != obj.getClass()){ return false; }
		Ministro other = (Ministro) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){ return false; }
		return true;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer pId){
		id = pId;
	}

	public Coordenador getCoordenador(){
		return coordenador;
	}

	public void setCoordenador(Coordenador pCoordenador){
		coordenador = pCoordenador;
	}

	public Date getDataNomeacao(){
		return dataNomeacao;
	}

	public void setDataNomeacao(Date pDataNomeacao){
		dataNomeacao = pDataNomeacao;
	}

	public Date getDatadataEntrega(){
		return datadataEntrega;
	}

	public void setDatadataEntrega(Date pDatadataEntrega){
		datadataEntrega = pDatadataEntrega;
	}

	public Date getDataOrdenacao(){
		return dataOrdenacao;
	}

	public void setDataOrdenacao(Date pDataOrdenacao){
		dataOrdenacao = pDataOrdenacao;
	}

	public Date getDataOrdenacaoEspecifica(){
		return dataOrdenacaoEspecifica;
	}

	public void setDataOrdenacaoEspecifica(Date pDataOrdenacaoEspecifica){
		dataOrdenacaoEspecifica = pDataOrdenacaoEspecifica;
	}

	public String[] getMinisterios(){
		return ministerios;
	}

	public void setMinisterios(String[] pMinisterios){
		ministerios = pMinisterios;
	}

	public Date getDataPresidentePosse(){
		return dataPresidentePosse;
	}

	public void setDataPresidentePosse(Date pDataPresidentePosse){
		dataPresidentePosse = pDataPresidentePosse;
	}

	public Date getDataPresidenteEntrega(){
		return dataPresidenteEntrega;
	}

	public void setDataPresidenteEntrega(Date pDataPresidenteEntrega){
		dataPresidenteEntrega = pDataPresidenteEntrega;
	}

	public List<Sede> getSedes(){
		return sedes;
	}

	public void setSedes(List<Sede> pSedes){
		sedes = pSedes;
	}

	public boolean isPresident(){
		return isPresident;
	}

	public void setPresident(boolean pIsPresident){
		isPresident = pIsPresident;
	}

	public boolean isConsagrated(){
		return isConsagrated;
	}

	public void setConsagrated(boolean pIsConsagrated){
		isConsagrated = pIsConsagrated;
	}

	public boolean isMinEspecifico(){
		return minEspecifico;
	}

	public void setMinEspecifico(boolean pMinEspecifico){
		minEspecifico = pMinEspecifico;
	}
}
