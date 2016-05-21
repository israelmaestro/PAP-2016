package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Lider implements Serializable{
	private static final long	serialVersionUID	= -6129701255435081024L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Flags
	@NotNull
	private boolean				isCoordinator		= false;

	// Informações pessoais do líder
	@NotNull
	@OneToOne // (fetch = FetchType.EAGER)
	private Membro				membro;

	// Atribuições
	@NotNull
	@OneToOne // (fetch = FetchType.EAGER)
	private Celula				celula;

	@OneToOne(/* fetch = FetchType.EAGER, */ cascade = CascadeType.ALL)
	private Coordenador			coordenador;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataPosse;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataEntrega;

	public Lider(){
		coordenador = new Coordenador();
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
		Lider other = (Lider) obj;
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

	public boolean isCoordinator(){
		return isCoordinator;
	}

	public void setCoordinator(boolean pIsCoordinator){
		isCoordinator = pIsCoordinator;
	}

	public Membro getMembro(){
		return membro;
	}

	public void setMembro(Membro pMembro){
		membro = pMembro;
	}

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
	}

	public Coordenador getCoordenador(){
		return coordenador;
	}

	public void setCoordenador(Coordenador pCoordenador){
		coordenador = pCoordenador;
	}

	public Date getDataPosse(){
		return dataPosse;
	}

	public void setDataPosse(Date pDataPosse){
		dataPosse = pDataPosse;
	}

	public Date getDataEntrega(){
		return dataEntrega;
	}

	public void setDataEntrega(Date pDataEntrega){
		dataEntrega = pDataEntrega;
	}
}
