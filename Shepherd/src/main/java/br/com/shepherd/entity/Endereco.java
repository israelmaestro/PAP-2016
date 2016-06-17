package br.com.shepherd.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Endereco implements Serializable{
	private static final long	serialVersionUID	= 484952411064066577L;

	@Id
	@GeneratedValue
	private Integer				id;

	@OneToOne
	private Celula				celula;

	@OneToOne
	private Sede				sede;

	@OneToOne
	private Pessoa				pessoa;

	@OneToOne
	private CelulaReuniao		celulaReuniao;

	@OneToOne
	private Atendimento			atendimento;

	private String				descricao;

	@NotNull
	private boolean				gps					= false;

	@Column(length = 8)
	private String				cep;

	private String				logradouro;

	@Column(length = 5)
	private Integer				numero;

	private String				complemento;

	private String				bairro;

	private String				cidade;

	@Column(length = 2)
	private String				estado;

	private String				pais;

	private String				coordenadas;

	public Endereco(){
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
		Endereco other = (Endereco) obj;
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

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public CelulaReuniao getCelulaReuniao(){
		return celulaReuniao;
	}

	public void setCelulaReuniao(CelulaReuniao pCelulaReuniao){
		celulaReuniao = pCelulaReuniao;
	}

	public Atendimento getAtendimento(){
		return atendimento;
	}

	public void setAtendimento(Atendimento pAtendimento){
		atendimento = pAtendimento;
	}

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String pDescricao){
		descricao = pDescricao;
	}

	public boolean isGps(){
		return gps;
	}

	public void setGps(boolean pGps){
		gps = pGps;
	}

	public String getCep(){
		return cep;
	}

	public void setCep(String pCep){
		cep = pCep;
	}

	public String getLogradouro(){
		return logradouro;
	}

	public void setLogradouro(String pLogradouro){
		logradouro = pLogradouro;
	}

	public Integer getNumero(){
		return numero;
	}

	public void setNumero(Integer pNumero){
		numero = pNumero;
	}

	public String getComplemento(){
		return complemento;
	}

	public void setComplemento(String pComplemento){
		complemento = pComplemento;
	}

	public String getBairro(){
		return bairro;
	}

	public void setBairro(String pBairro){
		bairro = pBairro;
	}

	public String getCidade(){
		return cidade;
	}

	public void setCidade(String pCidade){
		cidade = pCidade;
	}

	public String getEstado(){
		return estado;
	}

	public void setEstado(String pEstado){
		estado = pEstado;
	}

	public String getPais(){
		return pais;
	}

	public void setPais(String pPais){
		pais = pPais;
	}

	public String getCoordenadas(){
		return coordenadas;
	}

	public void setCoordenadas(String pCoordenadas){
		coordenadas = pCoordenadas;
	}
}
