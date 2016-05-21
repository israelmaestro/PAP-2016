package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.shepherd.enums.LiberacaoAcesso;

@Entity
public class Perfil implements Serializable{
	private static final long	serialVersionUID	= 7084305456081425633L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Enumerated(EnumType.STRING)
	private LiberacaoAcesso		acesso;

	@OneToMany(/* fetch = FetchType.EAGER, */ mappedBy = "perfil")
	private List<Usuario>		usuarios;

	public Perfil(){
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + (acesso == null ? 0 : acesso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){ return true; }
		if(obj == null){ return false; }
		if(getClass() != obj.getClass()){ return false; }
		Perfil other = (Perfil) obj;
		if(acesso != other.acesso){ return false; }
		return true;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer pId){
		id = pId;
	}

	public LiberacaoAcesso getAcesso(){
		return acesso;
	}

	public void setAcesso(LiberacaoAcesso pAcesso){
		acesso = pAcesso;
	}

	public List<Usuario> getUsuarios(){
		return usuarios;
	}

	public void setUsuarios(List<Usuario> pUsuarios){
		usuarios = pUsuarios;
	}
}
