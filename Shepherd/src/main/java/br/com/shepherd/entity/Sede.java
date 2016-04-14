package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.URL;

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

	@OneToOne(fetch = FetchType.EAGER)
	private Ministro			presidente;


	// Informações de endereço
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

	// Informações de contato
	@Column(length = 4)
	private String				telefoneDdi1;

	@Column(length = 9)
	private String				telefoneNumero1;

	private String				telefoneTipo1;

	@Email
	private String				email1;

	@URL
	private String				paginaWeb;

	@URL
	private String				perfilRedeSocial;

	// TODO: Lista de correspondências (cartas)

	// TODO: Lista de assembleias gerais

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

	public Date getDataFundacao(){
		return dataFundacao;
	}

	public void setDataFundacao(Date pDataFundacao){
		dataFundacao = pDataFundacao;
	}

	public List<Celula> getCelulas(){
		return celulas;
	}

	public void setCelulas(List<Celula> pCelulas){
		celulas = pCelulas;
	}

	public Ministro getPresidente(){
		return presidente;
	}

	public void setPresidente(Ministro pPresidente){
		presidente = pPresidente;
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

	public String getTelefoneDdi1(){
		return telefoneDdi1;
	}

	public void setTelefoneDdi1(String pTelefoneDdi1){
		telefoneDdi1 = pTelefoneDdi1;
	}

	public String getTelefoneNumero1(){
		return telefoneNumero1;
	}

	public void setTelefoneNumero1(String pTelefoneNumero1){
		telefoneNumero1 = pTelefoneNumero1;
	}

	public String getTelefoneTipo1(){
		return telefoneTipo1;
	}

	public void setTelefoneTipo1(String pTelefoneTipo1){
		telefoneTipo1 = pTelefoneTipo1;
	}

	public String getEmail1(){
		return email1;
	}

	public void setEmail1(String pEmail1){
		email1 = pEmail1;
	}

	public String getPaginaWeb(){
		return paginaWeb;
	}

	public void setPaginaWeb(String pPaginaWeb){
		paginaWeb = pPaginaWeb;
	}

	public String getPerfilRedeSocial(){
		return perfilRedeSocial;
	}

	public void setPerfilRedeSocial(String pPerfilRedeSocial){
		perfilRedeSocial = pPerfilRedeSocial;
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
