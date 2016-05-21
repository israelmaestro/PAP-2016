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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Visitante implements Serializable{
	private static final long	serialVersionUID	= -1189982959606136016L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Informações gerais
	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataVisita;

	@OneToOne // (fetch = FetchType.LAZY)
	private Sede				sedeVisita;

	// Situação
	private String				situacao;

	// Programação da igreja que o membro visitou
	private String				programacaoVisita;

	// Informações pessoais do visitante
	@NotNull
	@OneToOne // (fetch = FetchType.LAZY)
	private Pessoa				pessoa;

	// Informações do cadastrante
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	private Membro				membroCadastrante;

	@OneToOne // (fetch = FetchType.LAZY)
	private Celula				celula;

	// Informações de reuniões
	@ManyToMany // (fetch = FetchType.LAZY)
	private List<CelulaReuniao>	reunioesComparecidas;

	// Atendimentos
	@OneToMany(/* fetch = FetchType.LAZY, */ mappedBy = "visitanteAtendido")
	private List<Atendimento>	atendimentosRecebidos;

	// Comentários
	@Column(length = 1000)
	private String				comentarios;

	// Construtor e afins
	public Visitante(){
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
		Visitante other = (Visitante) obj;
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

	public Date getDataVisita(){
		return dataVisita;
	}

	public void setDataVisita(Date pDataVisita){
		dataVisita = pDataVisita;
	}

	public Sede getSedeVisita(){
		return sedeVisita;
	}

	public void setSedeVisita(Sede pSedeVisita){
		sedeVisita = pSedeVisita;
	}

	public String getSituacao(){
		return situacao;
	}

	public void setSituacao(String pSituacao){
		situacao = pSituacao;
	}

	public String getProgramacaoVisita(){
		return programacaoVisita;
	}

	public void setProgramacaoVisita(String pProgramacaoVisita){
		programacaoVisita = pProgramacaoVisita;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public Membro getMembroCadastrante(){
		return membroCadastrante;
	}

	public void setMembroCadastrante(Membro pMembroCadastrante){
		membroCadastrante = pMembroCadastrante;
	}

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
	}

	public List<CelulaReuniao> getReunioesComparecidas(){
		return reunioesComparecidas;
	}

	public void setReunioesComparecidas(List<CelulaReuniao> pReunioesComparecidas){
		reunioesComparecidas = pReunioesComparecidas;
	}

	public List<Atendimento> getAtendimentosRecebidos(){
		return atendimentosRecebidos;
	}

	public void setAtendimentosRecebidos(List<Atendimento> pAtendimentosRecebidos){
		atendimentosRecebidos = pAtendimentosRecebidos;
	}

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}
}
