package br.com.shepherd.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "conta" }) })
public class Usuario implements Serializable{
	private static final long	serialVersionUID	= 4899910755415143008L;

	@Id
	@NotNull
	@Column(length = 8)
	@NotEmpty
	private String				conta;

	@NotNull
	@Column(length = 32)
	private String				senha;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Perfil				perfil;

	// @NotNull
	@OneToOne(fetch = FetchType.LAZY)
	private Membro				membro;

	public Usuario(){
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + (conta == null ? 0 : conta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){ return true; }
		if(obj == null){ return false; }
		if(getClass() != obj.getClass()){ return false; }
		Usuario other = (Usuario) obj;
		if(conta == null){
			if(other.conta != null){ return false; }
		} else if(!conta.equals(other.conta)){ return false; }
		return true;
	}

	public String getConta(){
		return conta;
	}

	public void setConta(String pConta){
		conta = pConta;
	}

	public String getSenha(){
		return senha;
	}

	public void setSenha(String pSenha){
		senha = pSenha;
	}

	public Perfil getPerfil(){
		return perfil;
	}

	public void setPerfil(Perfil pPerfil){
		perfil = pPerfil;
	}

	public Membro getMembro(){
		return membro;
	}

	public void setMembro(Membro pMembro){
		membro = pMembro;
	}
}
