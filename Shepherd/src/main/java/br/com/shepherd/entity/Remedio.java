package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "principioAtivo" }) })
public class Remedio implements Serializable{
	private static final long	serialVersionUID	= 6917238330239953262L;

	@Id
	@GeneratedValue
	private Integer				id;

	@NotNull
	private String				nome;

	@NotNull
	private String				principioAtivo;

	private String				descricao;

	@Column(length = 7)
	private String				tarja;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "remedios")
	private List<Pessoa>		pessoas;

	public Remedio(){
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
		Remedio other = (Remedio) obj;
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

	public String getNome(){
		return nome;
	}

	public void setNome(String pNome){
		nome = pNome;
	}

	public String getPrincipioAtivo(){
		return principioAtivo;
	}

	public void setPrincipioAtivo(String pPrincipioAtivo){
		principioAtivo = pPrincipioAtivo;
	}

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String pDescricao){
		descricao = pDescricao;
	}

	public String getTarja(){
		return tarja;
	}

	public void setTarja(String pTarja){
		tarja = pTarja;
	}

	public List<Pessoa> getPessoas(){
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pPessoas){
		pessoas = pPessoas;
	}
}
