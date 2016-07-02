package br.com.shepherd.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Pessoa_Atendimento")
public class PessoaAtendimento implements Serializable{
	private static final long	serialVersionUID	= 5590231584975359435L;

	@Id
	@GeneratedValue
	private Integer				id;

	@ManyToOne
	private Pessoa				pessoa;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Atendimento			atendimento;

	// Participação: Atendente / Atendido
	@NotNull
	private String				participacao;

	/*
	 * Construtor e afins
	 */
	public PessoaAtendimento(){
		if(null == atendimento){
			atendimento = new Atendimento();
		}
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
		PessoaAtendimento other = (PessoaAtendimento) obj;
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

	public Atendimento getAtendimento(){
		return atendimento;
	}

	public void setAtendimento(Atendimento pAtendimento){
		atendimento = pAtendimento;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public String getParticipacao(){
		return participacao;
	}

	public void setParticipacao(String pParticipacao){
		participacao = pParticipacao;
	}
}
