package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "nome" }) })
public class Ministerio implements Serializable{
	private static final long serialVersionUID = 1832357384335694741L;

	@Id
	private String				nome;

	@ManyToMany(mappedBy = "ministerios")
	private List<Pessoa>		pessoas;

	public Ministerio(){
	}

	public String getNome(){
		return nome;
	}

	public void setNome(String pNome){
		nome = pNome;
	}

	public List<Pessoa> getPessoas(){
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pPessoas){
		pessoas = pPessoas;
	}
}
