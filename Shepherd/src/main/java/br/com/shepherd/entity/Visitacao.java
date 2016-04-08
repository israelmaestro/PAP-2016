package br.com.shepherd.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Visitacao implements Serializable{
	private static final long	serialVersionUID	= -1189982959606136016L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){ return true; }
		if(obj == null){ return false; }
		if(getClass() != obj.getClass()){ return false; }
		Visitacao other = (Visitacao) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){
			return false;
		}
		return true;
	}

	// Construtor e afins
	public Visitacao(){
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer pId){
		id = pId;
	}
}
