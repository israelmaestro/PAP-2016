package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Pessoa_Frente")
public class PessoaFrente implements Serializable{
	private static final long	serialVersionUID	= 5590231584975359435L;

	@Id
	@GeneratedValue
	private Integer				id;

	@ManyToOne
	private Pessoa				pessoa;

	@ManyToOne
	private Frente				frente;

	// Participação: Voluntário / Líder
	@NotNull
	private String				participacao;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataEntrada;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataSaida;

	/*
	 * Construtor e afins
	 */
	public PessoaFrente(){
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
		PessoaFrente other = (PessoaFrente) obj;
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

	public Frente getFrente(){
		return frente;
	}

	public void setFrente(Frente pFrente){
		frente = pFrente;
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

	public Date getDataEntrada(){
		return dataEntrada;
	}

	public void setDataEntrada(Date pDataEntrada){
		dataEntrada = pDataEntrada;
	}

	public Date getDataSaida(){
		return dataSaida;
	}

	public void setDataSaida(Date pDataSaida){
		dataSaida = pDataSaida;
	}
}
