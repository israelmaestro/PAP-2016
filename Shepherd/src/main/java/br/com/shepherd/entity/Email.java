package br.com.shepherd.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Email implements Serializable{
	private static final long	serialVersionUID	= 4899910755415143008L;

	@Id
	@GeneratedValue
	private Integer				id;

	@org.hibernate.validator.constraints.Email
	private String				conta;

	private String				descricao;

	@OneToOne // (fetch = FetchType.LAZY)
	private Sede					sede;

	@OneToOne // (fetch = FetchType.LAZY)
	private Pessoa				pessoa;

	public Email(){
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
		Email other = (Email) obj;
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

	public String getConta(){
		return conta;
	}

	public void setConta(String pConta){
		conta = pConta;
	}

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String pDescricao){
		descricao = pDescricao;
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}
}
