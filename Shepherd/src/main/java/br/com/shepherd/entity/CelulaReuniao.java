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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
// (uniqueConstraints = {
// @UniqueConstraint(columnNames = {
// "nome", "frente", "sede"
// })
// })
public class CelulaReuniao implements Serializable{
	private static final long	serialVersionUID	= -7390302449224224794L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Dados da Reunião
	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataHora;

	private String				titulo;

	@OneToOne(fetch = FetchType.LAZY)
	private Membro				anfitriao;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "reunioesComparecidas")
	private List<Membro>		membrosComparecidos;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "reunioesComparecidas")
	private List<Visitante>		visitantesComparecidos;

	@OneToOne(fetch = FetchType.LAZY)
	private Celula				celula;

	// Flags
	private boolean				isGpsAddress;

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

	@Column(length=1000)
	private String				comentarios;

	public CelulaReuniao(){
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
		CelulaReuniao other = (CelulaReuniao) obj;
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

	public Date getDataHora(){
		return dataHora;
	}

	public void setDataHora(Date pDataHora){
		dataHora = pDataHora;
	}

	public String getTitulo(){
		return titulo;
	}

	public void setTitulo(String pTitulo){
		titulo = pTitulo;
	}

	public Membro getAnfitriao(){
		return anfitriao;
	}

	public void setAnfitriao(Membro pAnfitriao){
		anfitriao = pAnfitriao;
	}

	public List<Membro> getMembrosComparecidos(){
		return membrosComparecidos;
	}

	public void setMembrosComparecidos(List<Membro> pMembrosComparecidos){
		membrosComparecidos = pMembrosComparecidos;
	}

	public List<Visitante> getVisitantesComparecidos(){
		return visitantesComparecidos;
	}

	public void setVisitantesComparecidos(List<Visitante> pVisitantesComparecidos){
		visitantesComparecidos = pVisitantesComparecidos;
	}

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
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

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}
}
