package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "nome", "sede_id" }) })
public class Frente implements Serializable{
	private static final long	serialVersionUID	= 6248982972855786486L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Dados da frente
	@NotNull
	private String				nome;

	@NotNull
	private String				descricao;

	@NotNull
	private boolean				ativa				= false;

	@NotNull
	@OneToOne
	private Sede				sede;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	// @NotNull
	private Date				dataAtivacao;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataDesativacao;

	@OneToMany(mappedBy = "frente")
	private List<Celula>		celulas;

	private Integer				idadeMinima;

	private Integer				idadeMaxima;

	// Flags
	@NotNull
	private boolean				isCell				= false;

	// TODO: Verificar se vai funcionar o casacade
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "frente", cascade = CascadeType.ALL)
	private List<PessoaFrente>	pessoasFrentes;

	// Construtor e afins
	public Frente(){
		pessoasFrentes = new ArrayList<PessoaFrente>();
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
		Frente other = (Frente) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){ return false; }
		return true;
	}

	/**
	 * Adiciona 1 relacionamento com Pessoa
	 *
	 * @param pPessoaFrente
	 * @param pParticipacao
	 */
	public void addRelacionamentoFrente(PessoaFrente pPessoaFrente, String pParticipacao){
		pessoasFrentes.add(pPessoaFrente);
		pPessoaFrente.setFrente(this);
		pPessoaFrente.setParticipacao(pParticipacao);
	}

	/**
	 * Remove 1 relacionamento de Pessoa
	 *
	 * @param pPessoa
	 */
	public void removeRelacionamentoFrente(PessoaFrente pPessoa){
		pessoasFrentes.remove(pPessoa);
	}

	// Getters e Setters
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

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String pDescricao){
		descricao = pDescricao;
	}

	public boolean isAtiva(){
		return ativa;
	}

	public void setAtiva(boolean pAtiva){
		ativa = pAtiva;
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}

	public Date getDataAtivacao(){
		return dataAtivacao;
	}

	public void setDataAtivacao(Date pDataAtivacao){
		dataAtivacao = pDataAtivacao;
	}

	public Date getDataDesativacao(){
		return dataDesativacao;
	}

	public void setDataDesativacao(Date pDataDesativacao){
		dataDesativacao = pDataDesativacao;
	}

	public List<Celula> getCelulas(){
		return celulas;
	}

	public void setCelulas(List<Celula> pCelulas){
		celulas = pCelulas;
	}

	public Integer getIdadeMinima(){
		return idadeMinima;
	}

	public void setIdadeMinima(Integer pIdadeMinima){
		idadeMinima = pIdadeMinima;
	}

	public Integer getIdadeMaxima(){
		return idadeMaxima;
	}

	public void setIdadeMaxima(Integer pIdadeMaxima){
		idadeMaxima = pIdadeMaxima;
	}

	public boolean isCell(){
		return isCell;
	}

	public void setCell(boolean pIsCell){
		isCell = pIsCell;
	}

	public List<PessoaFrente> getPessoasFrentes(){
		return pessoasFrentes;
	}

	public void setPessoasFrentes(List<PessoaFrente> pPessoasFrentes){
		pessoasFrentes = pPessoasFrentes;
	}
}
