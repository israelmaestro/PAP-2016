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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

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

	@OneToMany(/* fetch = FetchType.EAGER, */ mappedBy = "sede")
	private List<Celula>		celulas;

	@OneToOne // (fetch = FetchType.EAGER)
	private Ministro			presidente;

	@OneToOne // (fetch = FetchType.EAGER)
	private Sede				sedeMae;

	// Informações de endereço
	@Column(length = 8)
	private String				enderecoCep;

	private String				enderecoLogradouro;

	@Column(length = 5)
	private Integer				enderecoNumero;

	private String				enderecoComplemento;

	private String				enderecoBairro;

	private String				enderecoCidade;

	@Column(length = 2)
	private String				enderecoEstado;

	private String				enderecoPais;

	// Informações de telefone
	@OneToMany(/* fetch = FetchType.EAGER, */ mappedBy = "sede", cascade = CascadeType.ALL)
	private List<Telefone>		telefones;

	// Informações de email
	@OneToMany(/* fetch = FetchType.EAGER, */ mappedBy = "sede", cascade = CascadeType.ALL)
	private List<Email>			emails;

	@OneToMany( /* fetch = FetchType.EAGER, */
				mappedBy = "sede"/* , cascade = CascadeType.DETACH */)
	private List<Comunicado>	comunicados;

	@URL
	private String				paginaWeb;

	@URL
	private String				perfilRedeSocial;

	@Column(length = 1000)
	private String				comentarios;

	// TODO: Lista de assembleias gerais

	// Flags
	@NotNull
	private boolean				isMainChurch		= false;

	@NotNull
	private boolean				isAtiva				= false;

	@NotNull
	private boolean				isGpsAddress		= false;

	public Sede(){
		telefones = new ArrayList<Telefone>();
		emails = new ArrayList<Email>();
		setComunicados(new ArrayList<Comunicado>());
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

	public Sede getSedeMae(){
		return sedeMae;
	}

	public void setSedeMae(Sede pSedeMae){
		sedeMae = pSedeMae;
	}

	public String getEnderecoCep(){
		return enderecoCep;
	}

	public void setEnderecoCep(String pEnderecoCep){
		enderecoCep = pEnderecoCep;
	}

	public String getEnderecoLogradouro(){
		return enderecoLogradouro;
	}

	public void setEnderecoLogradouro(String pEnderecoLogradouro){
		enderecoLogradouro = pEnderecoLogradouro;
	}

	public Integer getEnderecoNumero(){
		return enderecoNumero;
	}

	public void setEnderecoNumero(Integer pEnderecoNumero){
		enderecoNumero = pEnderecoNumero;
	}

	public String getEnderecoComplemento(){
		return enderecoComplemento;
	}

	public void setEnderecoComplemento(String pEnderecoComplemento){
		enderecoComplemento = pEnderecoComplemento;
	}

	public String getEnderecoBairro(){
		return enderecoBairro;
	}

	public void setEnderecoBairro(String pEnderecoBairro){
		enderecoBairro = pEnderecoBairro;
	}

	public String getEnderecoCidade(){
		return enderecoCidade;
	}

	public void setEnderecoCidade(String pEnderecoCidade){
		enderecoCidade = pEnderecoCidade;
	}

	public String getEnderecoEstado(){
		return enderecoEstado;
	}

	public void setEnderecoEstado(String pEnderecoEstado){
		enderecoEstado = pEnderecoEstado;
	}

	public String getEnderecoPais(){
		return enderecoPais;
	}

	public void setEnderecoPais(String pEnderecoPais){
		enderecoPais = pEnderecoPais;
	}

	public List<Telefone> getTelefones(){
		return telefones;
	}

	public void setTelefones(List<Telefone> pTelefones){
		telefones = pTelefones;
	}

	public List<Email> getEmails(){
		return emails;
	}

	public void setEmails(List<Email> pEmails){
		emails = pEmails;
	}

	public List<Comunicado> getComunicados(){
		return comunicados;
	}

	public void setComunicados(List<Comunicado> pComunicados){
		comunicados = pComunicados;
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

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
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

	public void addTelefone(Telefone pTelefone){
		telefones.add(pTelefone);
		pTelefone.setSede(this);
	}

	public void removeTelefone(Telefone pTelefone){
		telefones.remove(pTelefone);
	}

	public void addEmail(Email pEmail){
		emails.add(pEmail);
		pEmail.setSede(this);
	}

	public void removeEmail(Email pEmail){
		emails.remove(pEmail);
	}
}
