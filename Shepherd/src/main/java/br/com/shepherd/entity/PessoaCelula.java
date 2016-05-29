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
@Table(name = "Pessoa_Celula")
public class PessoaCelula implements Serializable{
	private static final long	serialVersionUID	= 5590231584975359435L;

	@Id
	@GeneratedValue
	private Integer				id;

	@ManyToOne
	private Pessoa				pessoa;

	@ManyToOne
	private Celula				celula;

	@NotNull
	private String				participacao;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				dataInicio;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataFim;

	public PessoaCelula(){
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
		PessoaCelula other = (PessoaCelula) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){ return false; }
		return true;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
	}

	public String getParticipacao(){
		return participacao;
	}

	public void setParticipacao(String pParticipacao){
		participacao = pParticipacao;
	}

	public Date getDataInicio(){
		return dataInicio;
	}

	public void setDataInicio(Date pDataInicio){
		dataInicio = pDataInicio;
	}

	public Date getDataFim(){
		return dataFim;
	}

	public void setDataFim(Date pDataFim){
		dataFim = pDataFim;
	}
}
