package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Coordenador implements Serializable{
	private static final long	serialVersionUID	= -6129701255435081024L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Flags
	@NotNull
	private boolean				isPriest			= false;

	// Dados do coordenador
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	private Lider				lider;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataPosse;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataEntrega;

	// Atribuições
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Ministro			ministro;

	@NotNull
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coordenador")
	private List<Celula>		celulas;

	public Coordenador(){
		ministro = new Ministro();
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
		Coordenador other = (Coordenador) obj;
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

	public boolean isPriest(){
		return isPriest;
	}

	public void setPriest(boolean pIsPriest){
		isPriest = pIsPriest;
	}

	public Lider getLider(){
		return lider;
	}

	public void setLider(Lider pLider){
		lider = pLider;
	}

	public Ministro getMinistro(){
		return ministro;
	}

	public void setMinistro(Ministro pMinistro){
		ministro = pMinistro;
	}

	public List<Celula> getCelulas(){
		return celulas;
	}

	public void setCelulas(List<Celula> pCelulas){
		celulas = pCelulas;
	}
}
