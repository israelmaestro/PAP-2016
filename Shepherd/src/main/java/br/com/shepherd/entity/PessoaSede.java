package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Pessoa_Sede")
public class PessoaSede implements Serializable{
	private static final long	serialVersionUID	= 5590231584975359435L;

	/*
	 * ======================= Dados comuns para ambos =======================
	 */
	@Id
	@GeneratedValue
	private Integer				id;

	@ManyToOne
	private Pessoa				pessoa;

	@ManyToOne
	private Sede				sede;

	// Participação: Visitante / Ministério / Presbitério / Pastor Presidente
	@NotNull
	private String				participacao;

	@Column(length = 1000)
	private String				comentarios;

	/*
	 * ================ Para o visitante ================
	 */
	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataVisita;

	// Eventos Visitados padrão:
	// Culto dominical (manhã) / Culto dominical (noite) / Culto Jovem /
	// Culto Teen / Culto de Mulheres / Curas e Milagres / Reunião de célula
	@NotNull
	private String				eventoVisitado;

	// Situação: Nova conversão / Reconciliação
	private String				situacao;

	@NotNull
	private boolean				acompanhado			= false;

	@NotNull
	private boolean				comAtendimento		= false;

	// TODO: Como colocar um acompanhante


	/*
	 * ================ Para o Ministério ================
	 */
	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				dataInicio;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataFim;

	// Construtor e afins
	public PessoaSede(){
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
		PessoaSede other = (PessoaSede) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){ return false; }
		return true;
	}

	// Getters e Setters
	public Integer getId(){
		return id;
	}

	public void setId(Integer pId){
		id = pId;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}

	public String getParticipacao(){
		return participacao;
	}

	public void setParticipacao(String pParticipacao){
		participacao = pParticipacao;
	}

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}

	public Date getDataVisita(){
		return dataVisita;
	}

	public void setDataVisita(Date pDataVisita){
		dataVisita = pDataVisita;
	}

	public String getEventoVisitado(){
		return eventoVisitado;
	}

	public void setEventoVisitado(String pEventoVisitado){
		eventoVisitado = pEventoVisitado;
	}

	public String getSituacao(){
		return situacao;
	}

	public void setSituacao(String pSituacao){
		situacao = pSituacao;
	}

	public boolean isAcompanhado(){
		return acompanhado;
	}

	public void setAcompanhado(boolean pAcompanhado){
		acompanhado = pAcompanhado;
	}

	public boolean isComAtendimento(){
		return comAtendimento;
	}

	public void setComAtendimento(boolean pComAtendimento){
		comAtendimento = pComAtendimento;
	}

	public Date getDataInicio(){
		return dataInicio;
	}

	public void setDataInicio(Date pDataInicio){
		dataInicio = pDataInicio;
	}

	public Date getDataFim(){
		return dataFim;
	}

	public void setDataFim(Date pDataFim){
		dataFim = pDataFim;
	}
}
