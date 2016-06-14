package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Atendimento implements Serializable{
	private static final long	serialVersionUID	= -1189982959606136016L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataHora;

	@OneToOne(mappedBy = "celula", cascade = CascadeType.ALL)
	private Endereco				endereco;

	/*
	 * Status: Novo / Pendente / Agendado / Reagendado / Finalizado
	 */
	private String				status;

	@Column(length = 1000)
	private String				comentarios;

	// TODO: Colocar endereço

	// TODO: Verificar se vai funcionar o casacade
	@OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL)
	private List<PessoaAtendimento>	pessoasAtendimentos;

	// Construtor e afins
	public Atendimento(){
		endereco = new Endereco();
		endereco.setAtendimento(this);
		pessoasAtendimentos = new ArrayList<PessoaAtendimento>();
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
		Atendimento other = (Atendimento) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){
			return false;
		}
		return true;
	}

	/**
	 * Adiciona 1 pessoa no atendimento
	 *
	 * @param pPessoaAtendimento
	 */
	public void addPessoa(PessoaAtendimento pPessoaAtendimento){
		pessoasAtendimentos.add(pPessoaAtendimento);
		pPessoaAtendimento.setAtendimento(this);
	}

	/**
	 * Remove 1 pessoa do atendimento
	 *
	 * @param pPessoaAtendimento
	 */
	public void removePessoa(PessoaAtendimento pPessoaAtendimento){
		pessoasAtendimentos.remove(pPessoaAtendimento);
	}

	// Getters e Setters
	public Integer getId(){
		return id;
	}

	public void setId(Integer pId){
		id = pId;
	}

	public Date getDataHora(){
		return dataHora;
	}

	public void setDataHora(Date pDataHora){
		dataHora = pDataHora;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String pStatus){
		status = pStatus;
	}

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}
}
