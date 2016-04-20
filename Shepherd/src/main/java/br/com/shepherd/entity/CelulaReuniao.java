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

	private Membro				anfitriao;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Membro>		membrosComparecidos;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Visitante>		visitantesComparecidos;

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

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}
}
