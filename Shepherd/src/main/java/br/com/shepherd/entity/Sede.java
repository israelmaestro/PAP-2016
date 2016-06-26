package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

	@OneToMany(mappedBy = "sede")
	private List<Frente>		frentes;

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "sede", cascade = CascadeType.ALL)
	private List<Celula>		celulas;

	@OneToOne
	private Sede				sedeMae;

	@OneToOne(mappedBy = "sede", cascade = CascadeType.ALL)
	private Endereco			endereco;

	// Informações de telefone
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "sede", cascade = CascadeType.ALL)
	private List<Telefone>		telefones;

	// Informações de email
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "sede", cascade = CascadeType.ALL)
	private List<Email>			emails;

	// @OneToMany(mappedBy = "sede")
	// private List<Comunicado> comunicados;

	@URL
	private String				paginaWeb;

	@URL
	private String				perfilRedeSocial;

	@Column(length = 1000)
	private String				comentarios;

	// TODO: Lista de assembleias gerais

	@OneToMany(mappedBy = "sede", cascade = CascadeType.ALL)
	private List<PessoaSede>	pessoasSedes;

	// Flags
	@NotNull
	private boolean				mae					= false;

	@NotNull
	private boolean				ativa				= false;

	// construtor e afins
	public Sede(){
		endereco = new Endereco();
		endereco.setSede(this);
		telefones = new ArrayList<Telefone>();
		emails = new ArrayList<Email>();
		pessoasSedes = new ArrayList<PessoaSede>();
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

	/**
	 * Adiciona 1 pessoa na sede
	 *
	 * @param pPessoaSede
	 */
	public void addPessoa(PessoaSede pPessoaSede){
		pessoasSedes.add(pPessoaSede);
		pPessoaSede.setSede(this);
	}

	/**
	 * Remove 1 pessoa da sede
	 *
	 * @param pPessoaSede
	 */
	public void removePessoa(PessoaSede pPessoaSede){
		pessoasSedes.remove(pPessoaSede);
	}

	/**
	 * Adiciona 1 telefone à sede
	 *
	 * @param pTelefone
	 */
	public void addTelefone(Telefone pTelefone){
		telefones.add(pTelefone);
		pTelefone.setSede(this);
	}

	/**
	 * Remove 1 telefone da sede
	 *
	 * @param pTelefone
	 */
	public void removeTelefone(Telefone pTelefone){
		telefones.remove(pTelefone);
	}

	/**
	 * Adiciona 1 email à sede
	 *
	 * @param pEmail
	 */
	public void addEmail(Email pEmail){
		emails.add(pEmail);
		pEmail.setSede(this);
	}

	/**
	 * Remove 1 email da sede
	 *
	 * @param pEmail
	 */
	public void removeEmail(Email pEmail){
		emails.remove(pEmail);
	}

	// Getters e Setters
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

	public List<Frente> getFrentes(){
		return frentes;
	}

	public void setFrentes(List<Frente> pFrentes){
		frentes = pFrentes;
	}

	public List<Celula> getCelulas(){
		return celulas;
	}

	public void setCelulas(List<Celula> pCelulas){
		celulas = pCelulas;
	}

	public Sede getSedeMae(){
		return sedeMae;
	}

	public void setSedeMae(Sede pSedeMae){
		sedeMae = pSedeMae;
	}

	public Endereco getEndereco(){
		return endereco;
	}

	public void setEndereco(Endereco pEndereco){
		endereco = pEndereco;
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

	public List<PessoaSede> getPessoasSedes(){
		return pessoasSedes;
	}

	public void setPessoasSedes(List<PessoaSede> pPessoasSedes){
		pessoasSedes = pPessoasSedes;
	}

	public boolean isMae(){
		return mae;
	}

	public void setMae(boolean pMae){
		mae = pMae;
	}

	public boolean isAtiva(){
		return ativa;
	}

	public void setAtiva(boolean pAtiva){
		ativa = pAtiva;
	}
}
