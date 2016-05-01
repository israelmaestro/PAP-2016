package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comunicado implements Serializable{
	private static final long	serialVersionUID	= -1189982959606136016L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataHora;

	// @NotNull
	private String				codigo				= "";

	// @NotNull
	// @NotEmpty
	@Column(length = 10000)
	private String				conteudo;

	@OneToOne(fetch = FetchType.LAZY)
	private Sede				sede;

	@OneToOne(fetch = FetchType.LAZY)
	private Ministro			ministro;

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
		Comunicado other = (Comunicado) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){
			return false;
		}
		return true;
	}

	// Construtor e afins
	public Comunicado(){
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer pId){
		id = pId;
	}

	public Date getDataHora(){
		return dataHora;
	}

	public void setDataHora(Date pDataHora){
		dataHora = pDataHora;
	}

	public String getCodigo(){
		return codigo;
	}

	public void setCodigo(String pCodigo){
		codigo = pCodigo;
	}

	public String getConteudo(){
		return conteudo;
	}

	public void setConteudo(String pConteudo){
		conteudo = pConteudo;
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}
}
