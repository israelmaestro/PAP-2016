package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {	"nome",
																"sede_id" }) })
public class Celula implements Serializable{
	private static final long	serialVersionUID	= -7390302449224224794L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Dados da célula
	@NotNull
	private String				nome;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "celula")
	private List<Membro>		membros;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "celula")
	private List<Visitante>		visitantes;

	// @NotNull
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "celula")
	private List<Lider>			lideres;

	// @NotNull
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "celula")
	private List<CelulaReuniao>	reunioes;

	// @NotNull
	@OneToOne(fetch = FetchType.LAZY)
	private Coordenador			coordenador;

	// @NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Frente				frente;

	// @NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Sede				sede;

	private boolean				isActive			= false;

	private boolean				isVisitorsAllowed	= false;

	private boolean				isEnderecoFixo		= false;

	private boolean				isGpsAddress		= false;

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

	@Column(length = 1000)
	private String				comentarios;

	public Celula(){
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
		Celula other = (Celula) obj;
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

	public List<Membro> getMembros(){
		return membros;
	}

	public void setMembros(List<Membro> pMembros){
		membros = pMembros;
	}

	public List<Lider> getLideres(){
		return lideres;
	}

	public void setLideres(List<Lider> pLideres){
		lideres = pLideres;
	}

	public List<Visitante> getVisitantes(){
		return visitantes;
	}

	public void setVisitantes(List<Visitante> pVisitantes){
		visitantes = pVisitantes;
	}

	public List<CelulaReuniao> getReunioes(){
		return reunioes;
	}

	public void setReunioes(List<CelulaReuniao> pReunioes){
		reunioes = pReunioes;
	}

	public Coordenador getCoordenador(){
		return coordenador;
	}

	public void setCoordenador(Coordenador pCoordenador){
		coordenador = pCoordenador;
	}

	public Frente getFrente(){
		return frente;
	}

	public void setFrente(Frente pFrente){
		frente = pFrente;
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}

	public boolean isActive(){
		return isActive;
	}

	public void setActive(boolean pIsActive){
		isActive = pIsActive;
	}

	public boolean isVisitorsAllowed(){
		return isVisitorsAllowed;
	}

	public void setVisitorsAllowed(boolean pIsVisitorsAllowed){
		isVisitorsAllowed = pIsVisitorsAllowed;
	}

	public boolean isEnderecoFixo(){
		return isEnderecoFixo;
	}

	public void setEnderecoFixo(boolean pIsEnderecoFixo){
		isEnderecoFixo = pIsEnderecoFixo;
	}

	public boolean isGpsAddress(){
		return isGpsAddress;
	}

	public void setGpsAddress(boolean pIsGpsAddress){
		isGpsAddress = pIsGpsAddress;
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

	public void setEnderecoCidade(String pCidade){
		enderecoCidade = pCidade;
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

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}
}
