package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "nome" }) })
public class NEspecial implements Serializable{
	private static final long	serialVersionUID	= -3507818811201882770L;

	@Id
	@GeneratedValue
	@NotNull
	private Integer				id;

	@NotNull
	private String				nome;

	@NotNull
	private String				tipo;

	@NotNull
	private String				descricao;

	@ManyToMany(/* fetch = FetchType.EAGER, */ mappedBy = "nEspeciais")
	private List<Pessoa>		pessoas;

	@NotNull
	private boolean				deficiencia;

	public NEspecial(){
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
		NEspecial other = (NEspecial) obj;
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

	public String getTipo(){
		return tipo;
	}

	public void setTipo(String pTipo){
		tipo = pTipo;
	}

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String pDescricao){
		descricao = pDescricao;
	}

	public List<Pessoa> getPessoas(){
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pPessoas){
		pessoas = pPessoas;
	}

	public boolean isDeficiencia(){
		return deficiencia;
	}

	public void setDeficiencia(boolean pDeficiencia){
		deficiencia = pDeficiencia;
	}
}
