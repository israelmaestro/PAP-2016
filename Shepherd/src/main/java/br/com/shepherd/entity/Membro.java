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

	@ManyToMany(fetch = FetchType.EAGER)
	private List<CelulaReuniao>	reunioesComparecidas;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "anfitriao")
	private List<CelulaReuniao>	reunioesRecebidas;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataMembresia;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataDesligamento;

	// Informaï¿½ï¿½es pessoais do membro
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Pessoa				pessoa;

	// Flags
	@NotNull
	private boolean				isBaptized			= false;

	@NotNull
	private boolean				isLeader			= false;

	@NotNull
	private boolean				isTrainee			= false;

	@NotNull
	private boolean				isProjectLeader		= false;

	@NotNull // TODO: Continuar a partir daqui.
	private boolean				isUser				= false;

	private boolean				isDirectoryMember	= false;

	// Atribuiï¿½ï¿½es
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Frente>		frentesParticipadas;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "lideres")
	private List<Frente>		frentesLideradas;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Lider				lider;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Usuario				usuario;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "membroAtendido")
	private List<Atendimento>	atendimentosRecebidos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "membroAtendente")
	private List<Atendimento>	atendimentosRealizados;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "membroCadastrante")
	private List<Visitante>		visitantesCadastrados;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataColiderancaPosse;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataColiderancaEntrega;

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

	public List<CelulaReuniao> getReunioesComparecidas(){
		return reunioesComparecidas;
	}

	public void setReunioesComparecidas(List<CelulaReuniao> pReunioesComparecidas){
		reunioesComparecidas = pReunioesComparecidas;
	}

	public Date getDataMembresia(){
		return dataMembresia;
	}

	public void setDataMembresia(Date pDataMembresia){
		dataMembresia = pDataMembresia;
	}

	public Date getDataDesligamento(){
		return dataDesligamento;
	}

	public void setDataDesligamento(Date pDataDesligamento){
		dataDesligamento = pDataDesligamento;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public boolean isBaptized(){
		return isBaptized;
	}

	public void setBaptized(boolean pIsBaptized){
		isBaptized = pIsBaptized;
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

	public List<Frente> getFrentesParticipadas(){
		return frentesParticipadas;
	}

	public void setFrentesParticipadas(List<Frente> pFrentesParticipadas){
		frentesParticipadas = pFrentesParticipadas;
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

	public List<Atendimento> getAtendimentosRecebidos(){
		return atendimentosRecebidos;
	}

	public void setAtendimentosRecebidos(List<Atendimento> pAtendimentosRecebidos){
		atendimentosRecebidos = pAtendimentosRecebidos;
	}

	public List<Atendimento> getAtendimentosRealizados(){
		return atendimentosRealizados;
	}

	public void setAtendimentosRealizados(List<Atendimento> pAtendimentosRealizados){
		atendimentosRealizados = pAtendimentosRealizados;
	}

	public List<Visitante> getVisitantesCadastrados(){
		return visitantesCadastrados;
	}

	public void setVisitantesCadastrados(List<Visitante> pVisitantesCadastrados){
		visitantesCadastrados = pVisitantesCadastrados;
	}

	public Date getDataColiderancaPosse(){
		return dataColiderancaPosse;
	}

	public void setDataColiderancaPosse(Date pDataColiderancaPosse){
		dataColiderancaPosse = pDataColiderancaPosse;
	}

	public Date getDataColiderancaEntrega(){
		return dataColiderancaEntrega;
	}

	public void setDataColiderancaEntrega(Date pDataColiderancaEntrega){
		dataColiderancaEntrega = pDataColiderancaEntrega;
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
