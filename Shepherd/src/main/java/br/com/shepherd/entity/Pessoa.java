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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {	"nome",
																"sobrenome",
																"dataNasc",
																"rg",
																"cpf",
																"sexo" }) })
public class Pessoa implements Serializable{
	private static final long					serialVersionUID		= 4400200519765426304L;

	@Id
	@GeneratedValue
	private Integer								id;

	// Dados pessoais
	@NotNull
	@NotEmpty
	private String								nome;

	@NotNull
	@NotEmpty
	private String								sobrenome;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date								dataNasc;

	@NotNull
	private boolean								sexo					= true;

	@Column(length = 9, unique = true)
	private String								rg;

	@Column(length = 11, unique = true)
	private String								cpf;

	@ManyToMany
	private List<NEspecial>						nEspeciais;
	//
	// @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	// private List<Endereco> enderecos;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private Endereco							endereco;

	// Informações de telefone
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Telefone>						telefones;

	// Informações de email
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<br.com.shepherd.entity.Email>	emails;

	// Informações de email
	@ManyToMany
	private List<Ministerio>					ministerios;

	// Flags
	@NotNull
	private boolean								ativa					= false;

	@NotNull
	private boolean								ordenadaAoMinisterio	= false;

	@NotNull
	private boolean								minEspecifico			= false;

	@NotNull
	private boolean								batizada				= false;

	@NotNull
	private boolean								casada					= false;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date								dataCasamento;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date								dataBatismo;

	@OneToMany(mappedBy = "pessoa")
	private List<PessoaCelula>					pessoasCelulas;

	@Column(length = 1000)
	private String								comentarios;

	// Construtor e afins
	public Pessoa(){
		endereco = new Endereco();
		endereco.setPessoa(this);
		telefones = new ArrayList<Telefone>();
		emails = new ArrayList<Email>();
		pessoasCelulas = new ArrayList<PessoaCelula>();
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
		Pessoa other = (Pessoa) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){ return false; }
		return true;
	}
	//
	// /**
	// * Adiciona 1 endereço na pessoa
	// *
	// * @param pEndereco
	// */
	// public void addEndereco(Endereco pEndereco){
	// enderecos.add(pEndereco);
	// pEndereco.setPessoa(this);
	// }
	//
	// /**
	// * Remove 1 endereço da pessoa
	// *
	// * @param pEndereco
	// */
	// public void removeEndereco(Endereco pEndereco){
	// enderecos.remove(pEndereco);
	// }

	/**
	 * Adiciona 1 relacionamento com a célula
	 *
	 * @param pTelefone
	 */
	public void addRelacionamentoCelula(PessoaCelula pPessoaCelula){
		pessoasCelulas.add(pPessoaCelula);
		pPessoaCelula.setPessoa(this);
	}

	/**
	 * Remove 1 relacionamento com a célula
	 *
	 * @param pTelefone
	 */
	public void removeRelacionamentoCelula(PessoaCelula pPessoaCelula){
		pessoasCelulas.remove(pPessoaCelula);
	}

	/**
	 * Adiciona 1 telefone na pessoa
	 *
	 * @param pTelefone
	 */
	public void addTelefone(Telefone pTelefone){
		telefones.add(pTelefone);
		pTelefone.setPessoa(this);
	}

	/**
	 * Remove 1 telefone da pessoa
	 *
	 * @param pTelefone
	 */
	public void removeTelefone(Telefone pTelefone){
		telefones.remove(pTelefone);
	}

	/**
	 * Adiciona 1 email na pessoa
	 *
	 * @param pEmail
	 */
	public void addEmail(br.com.shepherd.entity.Email pEmail){
		emails.add(pEmail);
		pEmail.setPessoa(this);
	}

	/**
	 * Remove 1 email da pessoa
	 *
	 * @param pEmail
	 */
	public void removeEmail(br.com.shepherd.entity.Email pEmail){
		emails.remove(pEmail);
	}

	// Getters e setters
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

	public String getSobrenome(){
		return sobrenome;
	}

	public void setSobrenome(String pSobrenome){
		sobrenome = pSobrenome;
	}

	public Date getDataNasc(){
		return dataNasc;
	}

	public void setDataNasc(Date pDataNasc){
		dataNasc = pDataNasc;
	}

	public boolean isSexo(){
		return sexo;
	}

	public void setSexo(boolean pSexo){
		sexo = pSexo;
	}

	public String getRg(){
		return rg;
	}

	public void setRg(String pRg){
		rg = pRg;
	}

	public String getCpf(){
		return cpf;
	}

	public void setCpf(String pCpf){
		cpf = pCpf;
	}

	public List<NEspecial> getnEspeciais(){
		return nEspeciais;
	}

	public void setnEspeciais(List<NEspecial> pNEspeciais){
		nEspeciais = pNEspeciais;
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

	public List<br.com.shepherd.entity.Email> getEmails(){
		return emails;
	}

	public void setEmails(List<br.com.shepherd.entity.Email> pEmails){
		emails = pEmails;
	}

	public List<Ministerio> getMinisterios(){
		return ministerios;
	}

	public void setMinisterios(List<Ministerio> pMinisterios){
		ministerios = pMinisterios;
	}

	public boolean isAtiva(){
		return ativa;
	}

	public void setAtiva(boolean pAtiva){
		ativa = pAtiva;
	}

	public boolean isOrdenadaAoMinisterio(){
		return ordenadaAoMinisterio;
	}

	public void setOrdenadaAoMinisterio(boolean pOrdenadaAoMinisterio){
		ordenadaAoMinisterio = pOrdenadaAoMinisterio;
	}

	public boolean isMinEspecifico(){
		return minEspecifico;
	}

	public void setMinEspecifico(boolean pMinEspecifico){
		minEspecifico = pMinEspecifico;
	}

	public boolean isBatizada(){
		return batizada;
	}

	public void setBatizada(boolean pBatizada){
		batizada = pBatizada;
	}

	public boolean isCasada(){
		return casada;
	}

	public void setCasada(boolean pCasada){
		casada = pCasada;
	}

	public Date getDataCasamento(){
		return dataCasamento;
	}

	public void setDataCasamento(Date pDataCasamento){
		dataCasamento = pDataCasamento;
	}

	public Date getDataBatismo(){
		return dataBatismo;
	}

	public void setDataBatismo(Date pDataBatismo){
		dataBatismo = pDataBatismo;
	}

	public List<PessoaCelula> getPessoasCelulas(){
		return pessoasCelulas;
	}

	public void setPessoasCelulas(List<PessoaCelula> pPessoasCelulas){
		pessoasCelulas = pPessoasCelulas;
	}

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}
}
