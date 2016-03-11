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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Membro implements Serializable{
	private static final long	serialVersionUID	= -1189982959606136016L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Informações gerais
	@OneToOne(fetch = FetchType.EAGER)
	private Celula				celula;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataEntrada;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataSaida;

	// Informações pessoais do membro
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Pessoa				pessoa;

	// Flags
	@NotNull
	private boolean				isLeader			= false;

	@NotNull
	private boolean				isTrainee			= false;

	@NotNull
	private boolean				isProjectLeader		= false;

	@NotNull
	private boolean				isUser				= false;

	private boolean				isDirectoryMember	= false;

	// Atribuições
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Frente>		frentes;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "lideres")
	private List<Frente>		frentesLideradas;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Lider				lider;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Usuario				usuario;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataColiderancaEntrada;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataColiderancaSaida;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataDiretoriaEntrada;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataDiretoriaSaida;

	private String				diretoriaCargo;

	// Construtor e afins
	public Membro(){
		lider = new Lider();
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
		Membro other = (Membro) obj;
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

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
	}

	public Date getDataEntrada(){
		return dataEntrada;
	}

	public void setDataEntrada(Date pDataEntrada){
		dataEntrada = pDataEntrada;
	}

	public Date getDataSaida(){
		return dataSaida;
	}

	public void setDataSaida(Date pDataSaida){
		dataSaida = pDataSaida;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public boolean isLeader(){
		return isLeader;
	}

	public void setLeader(boolean pIsLeader){
		isLeader = pIsLeader;
	}

	public boolean isTrainee(){
		return isTrainee;
	}

	public void setTrainee(boolean pIsTrainee){
		isTrainee = pIsTrainee;
	}

	public boolean isProjectLeader(){
		return isProjectLeader;
	}

	public void setProjectLeader(boolean pIsProjectLeader){
		isProjectLeader = pIsProjectLeader;
	}

	public boolean isUser(){
		return isUser;
	}

	public void setUser(boolean pIsUser){
		isUser = pIsUser;
	}

	public boolean isDirectoryMember(){
		return isDirectoryMember;
	}

	public void setDirectoryMember(boolean pIsDirectoryMember){
		isDirectoryMember = pIsDirectoryMember;
	}

	public List<Frente> getFrentes(){
		return frentes;
	}

	public void setFrentes(List<Frente> pFrentes){
		frentes = pFrentes;
	}

	public List<Frente> getFrentesLideradas(){
		return frentesLideradas;
	}

	public void setFrentesLideradas(List<Frente> pFrentesLideradas){
		frentesLideradas = pFrentesLideradas;
	}

	public Lider getLider(){
		return lider;
	}

	public void setLider(Lider pLider){
		lider = pLider;
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public void setUsuario(Usuario pUsuario){
		usuario = pUsuario;
	}

	public Date getDataColiderancaEntrada(){
		return dataColiderancaEntrada;
	}

	public void setDataColiderancaEntrada(Date pDataColiderancaEntrada){
		dataColiderancaEntrada = pDataColiderancaEntrada;
	}

	public Date getDataColiderancaSaida(){
		return dataColiderancaSaida;
	}

	public void setDataColiderancaSaida(Date pDataColiderancaSaida){
		dataColiderancaSaida = pDataColiderancaSaida;
	}

	public Date getDataDiretoriaEntrada(){
		return dataDiretoriaEntrada;
	}

	public void setDataDiretoriaEntrada(Date pDataDiretoriaEntrada){
		dataDiretoriaEntrada = pDataDiretoriaEntrada;
	}

	public Date getDataDiretoriaSaida(){
		return dataDiretoriaSaida;
	}

	public void setDataDiretoriaSaida(Date pDataDiretoriaSaida){
		dataDiretoriaSaida = pDataDiretoriaSaida;
	}

	public String getDiretoriaCargo(){
		return diretoriaCargo;
	}

	public void setDiretoriaCargo(String pDiretoriaCargo){
		diretoriaCargo = pDiretoriaCargo;
	}
}
