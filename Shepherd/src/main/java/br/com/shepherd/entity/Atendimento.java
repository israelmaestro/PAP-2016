package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Atendimento implements Serializable{
	private static final long	serialVersionUID	= -1189982959606136016L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataHora;

	// Informações gerais
	@OneToOne // (fetch = FetchType.LAZY)
	private Visitante			visitanteAtendido;

	// Informações gerais
	@OneToOne // (fetch = FetchType.LAZY)
	private Membro				membroAtendido;

	// Informações gerais
	@OneToOne // (fetch = FetchType.LAZY)
	private Membro				membroAtendente;

	private String				status;

	@Column(length = 1000)
	private String				comentarios;

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
		Atendimento other = (Atendimento) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){
			return false;
		}
		return true;
	}

	// Construtor e afins
	public Atendimento(){
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

	public Visitante getVisitanteAtendido(){
		return visitanteAtendido;
	}

	public void setVisitanteAtendido(Visitante pVisitanteAtendido){
		visitanteAtendido = pVisitanteAtendido;
	}

	public Membro getMembroAtendido(){
		return membroAtendido;
	}

	public void setMembroAtendido(Membro pMembroAtendido){
		membroAtendido = pMembroAtendido;
	}

	public Membro getMembroAtendente(){
		return membroAtendente;
	}

	public void setMembroAtendente(Membro pMembroAtendente){
		membroAtendente = pMembroAtendente;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String pStatus){
		status = pStatus;
	}

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
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
}
