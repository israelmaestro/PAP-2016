package br.com.shepherd.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "registro_presenca")
public class RegistroPresenca implements Serializable{
	private static final long	serialVersionUID	= 5590231584975359435L;

	@Id
	@GeneratedValue
	private Integer				id;

	@OneToOne
	private Pessoa				pessoa;

	@NotNull
	private boolean				presente;

	@NotNull
	private boolean				anfitriao;

	@OneToOne
	private CelulaReuniao		celulaReuniao;

	/*
	 * Construtor e afins
	 */
	public RegistroPresenca(){
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
		RegistroPresenca other = (RegistroPresenca) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){ return false; }
		return true;
	}

	// Getters e Setters
	public Integer getId(){
		return id;
	}

	public void setId(Integer pId){
		id = pId;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public boolean isPresente(){
		return presente;
	}

	public void setPresente(boolean pPresente){
		presente = pPresente;
	}

	public boolean isAnfitriao(){
		return anfitriao;
	}

	public void setAnfitriao(boolean pAnfitriao){
		anfitriao = pAnfitriao;
	}

	public CelulaReuniao getCelulaReuniao(){
		return celulaReuniao;
	}

	public void setCelulaReuniao(CelulaReuniao pCelulaReuniao){
		celulaReuniao = pCelulaReuniao;
	}
}
