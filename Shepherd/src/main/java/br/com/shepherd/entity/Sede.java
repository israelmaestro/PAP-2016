package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "nome", "cnpj" }) })
public class Sede implements Serializable{
	private static final long	serialVersionUID	= -7390302449224224794L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Dados da sede
	@NotNull
	private String				nome;

	@Column(length = 14, unique = true)
	private String				cnpj;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataFundacao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sede")
	private List<Celula>		celulas;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Ministro>		presidentes;

	// Flags
	@NotNull
	private boolean				isMainChurch		= false;

	@NotNull
	private boolean				isAtiva				= false;

	@NotNull
	private boolean				isGpsAddress		= false;

	public Sede(){
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
		Sede other = (Sede) obj;
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

	public String getCnpj(){
		return cnpj;
	}

	public void setCnpj(String pCnpj){
		cnpj = pCnpj;
	}

	public List<Celula> getCelulas(){
		return celulas;
	}

	public List<Ministro> getMinistros(){
		return presidentes;
	}

	public void setMinistros(List<Ministro> pMinistros){
		presidentes = pMinistros;
	}

	public void setCelulas(List<Celula> pCelulas){
		celulas = pCelulas;
	}

	public Date getDataFundacao(){
		return dataFundacao;
	}

	public void setDataFundacao(Date pDataFundacao){
		dataFundacao = pDataFundacao;
	}

	public boolean isMainChurch(){
		return isMainChurch;
	}

	public void setMainChurch(boolean pIsMainChurch){
		isMainChurch = pIsMainChurch;
	}

	public boolean isAtiva(){
		return isAtiva;
	}

	public void setAtiva(boolean pIsAtiva){
		isAtiva = pIsAtiva;
	}

	public boolean isGpsAddress(){
		return isGpsAddress;
	}

	public void setGpsAddress(boolean pIsGpsAddress){
		isGpsAddress = pIsGpsAddress;
	}
}
