package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = {
								@UniqueConstraint(columnNames = {
																	"nome", "sobrenome", "dataNasc",
																	"rg", "cpf", "sexo"
								})
})
public class Pessoa implements Serializable{
	private static final long	serialVersionUID	= 4400200519765426304L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Dados pessoais
	@NotNull
	@NotEmpty
	private String				nome;

	@NotNull
	@NotEmpty
	private String				sobrenome;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataNasc;

	@Column(length = 11, unique = true)
	private String				cpf;

	@Column(length = 9, unique = true)
	private String				rg;

	@NotNull
	private boolean				sexo;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<NEspecial>		nEspeciais;

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

	// Informações de contato
	@Column(length = 4)
	private String				telefoneDdi1;

	@Column(length = 9)
	private String				telefoneNumero1;

	private String				telefoneTipo1;

	@Column(length = 3)
	private String				telefoneDdi2;

	@Column(length = 9)
	private String				telefoneNumero2;

	private String				telefoneTipo2;

	@Column(length = 3)
	private String				telefoneDdi3;

	@Column(length = 9)
	private String				telefoneNumero3;

	private String				telefoneTipo3;

	@Email
	private String				email1;

	@Email
	private String				email2;

	// Flags
	@NotNull
	private boolean				ativo				= false;

	@NotNull
	private boolean				isMember			= false;

	@NotNull
	private boolean				isVisitor			= false;

	@NotNull
	private boolean				isMarried			= false;

	@NotNull
	private boolean				isGpsAddress		= false;

	// Informações de parentesco
	@OneToOne(fetch = FetchType.LAZY)
	private Pessoa				pai;

	@OneToOne(fetch = FetchType.LAZY)
	private Pessoa				mae;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Pessoa>		irmaos;

	@OneToOne(fetch = FetchType.LAZY)
	private Pessoa				conjuge;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataCasamento;

	// Informações eclesiásticas
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Membro				membro;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Visitante			visitante;

	// Construtor e afins
	public Pessoa(){
		membro = new Membro();
		visitante = new Visitante();
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

	public String getCpf(){
		return cpf;
	}

	public void setCpf(String pCpf){
		cpf = pCpf;
	}

	public String getRg(){
		return rg;
	}

	public void setRg(String pRg){
		rg = pRg;
	}

	public boolean getSexo(){
		return sexo;
	}

	public void setSexo(boolean pSexo){
		sexo = pSexo;
	}

	public List<NEspecial> getnEspeciais(){
		return nEspeciais;
	}

	public void setnEspeciais(List<NEspecial> pNEspeciais){
		nEspeciais = pNEspeciais;
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

	public String getTelefoneDdi2(){
		return telefoneDdi2;
	}

	public void setTelefoneDdi2(String pTelefoneDdi2){
		telefoneDdi2 = pTelefoneDdi2;
	}

	public String getTelefoneNumero2(){
		return telefoneNumero2;
	}

	public void setTelefoneNumero2(String pTelefoneNumero2){
		telefoneNumero2 = pTelefoneNumero2;
	}

	public String getTelefoneTipo2(){
		return telefoneTipo2;
	}

	public void setTelefoneTipo2(String ptelefoneTipo2){
		telefoneTipo2 = ptelefoneTipo2;
	}

	public String getTelefoneDdi3(){
		return telefoneDdi3;
	}

	public void setTelefoneDdi3(String pTelefoneDdi3){
		telefoneDdi3 = pTelefoneDdi3;
	}

	public String getTelefoneNumero3(){
		return telefoneNumero3;
	}

	public void setTelefoneNumero3(String pTelefoneNumero3){
		telefoneNumero3 = pTelefoneNumero3;
	}

	public String getTelefoneTipo3(){
		return telefoneTipo3;
	}

	public void setTelefoneTipo3(String pTelefoneTipo3){
		telefoneTipo3 = pTelefoneTipo3;
	}

	public String getEmail1(){
		return email1;
	}

	public void setEmail1(String pEmail1){
		email1 = pEmail1;
	}

	public String getEmail2(){
		return email2;
	}

	public void setEmail2(String pEmail2){
		email2 = pEmail2;
	}

	public boolean isAtivo(){
		return ativo;
	}

	public void setAtivo(boolean pAtivo){
		ativo = pAtivo;
	}

	public boolean isMember(){
		return isMember;
	}

	public void setMember(boolean pIsMember){
		isMember = pIsMember;
	}

	public boolean isVisitor(){
		return isVisitor;
	}

	public void setVisitor(boolean pIsVisitor){
		isVisitor = pIsVisitor;
	}

	public boolean isMarried(){
		return isMarried;
	}

	public void setMarried(boolean pIsMarried){
		isMarried = pIsMarried;
	}

	public boolean isGpsAddress(){
		return isGpsAddress;
	}

	public void setGpsAddress(boolean pIsGpsAddress){
		isGpsAddress = pIsGpsAddress;
	}

	public Pessoa getPai(){
		return pai;
	}

	public void setPai(Pessoa pPai){
		pai = pPai;
	}

	public Pessoa getMae(){
		return mae;
	}

	public void setMae(Pessoa pMae){
		mae = pMae;
	}

	public List<Pessoa> getIrmaos(){
		return irmaos;
	}

	public void setIrmaos(List<Pessoa> pIrmaos){
		irmaos = pIrmaos;
	}

	public Pessoa getConjuge(){
		return conjuge;
	}

	public void setConjuge(Pessoa pConjuge){
		conjuge = pConjuge;
	}

	public Date getDataCasamento(){
		return dataCasamento;
	}

	public void setDataCasamento(Date pDataCasamento){
		dataCasamento = pDataCasamento;
	}

	public Membro getMembro(){
		return membro;
	}

	public void setMembro(Membro pMembro){
		membro = pMembro;
	}

	public Visitante getVisitante(){
		return visitante;
	}

	public void setVisitante(Visitante pVisitante){
		visitante = pVisitante;
	}
}
