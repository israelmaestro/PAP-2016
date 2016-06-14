package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Celula implements Serializable{
	private static final long	serialVersionUID	= -7390302449224224794L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Dados da célula
	@NotNull
	private String				nome;

	@NotNull
	@OneToOne
	private Frente				frente;

	@NotNull
	@OneToOne
	private Sede				sede;

	@OneToOne(mappedBy = "celula", cascade = CascadeType.ALL)
	private Endereco			endereco;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				dataAtivação;

	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataDesativacao;

	// TODO: Verificar se vai funcionar o casacade
	@OneToMany(mappedBy = "celula", cascade = CascadeType.ALL)
	private List<PessoaCelula>	pessoasCelulas;

	@OneToMany(mappedBy = "celula", cascade = CascadeType.ALL)
	private List<CelulaReuniao>	reunioes;

	@Column(length = 1000)
	private String				comentarios;

	private boolean				ativa				= false;

	private boolean				comVisitante		= false;

	private boolean				comEnderecoFixo		= false;

	// Construtor e afins
	public Celula(){
		endereco = new Endereco();
		endereco.setCelula(this);
		pessoasCelulas = new ArrayList<PessoaCelula>();
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

	/**
	 * Adiciona 1 pessoa na célula
	 *
	 * @param pPessoaCelula
	 */
	public void addPessoa(PessoaCelula pPessoaCelula){
		pessoasCelulas.add(pPessoaCelula);
		pPessoaCelula.setCelula(this);
	}

	/**
	 * Remove 1 pessoa da célula
	 *
	 * @param pPessoaCelula
	 */
	public void removePessoa(PessoaCelula pPessoaCelula){
		pessoasCelulas.remove(pPessoaCelula);
	}

	// Getters e setters
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

	public Endereco getEndereco(){
		return endereco;
	}

	public void setEndereco(Endereco pEndereco){
		endereco = pEndereco;
	}

	public Date getDataAtivação(){
		return dataAtivação;
	}

	public void setDataAtivação(Date pDataAtivação){
		dataAtivação = pDataAtivação;
	}

	public Date getDataDesativacao(){
		return dataDesativacao;
	}

	public void setDataDesativacao(Date pDataDesativacao){
		dataDesativacao = pDataDesativacao;
	}

	public List<PessoaCelula> getPessoasCelulas(){
		return pessoasCelulas;
	}

	public void setPessoasCelulas(List<PessoaCelula> pPessoasCelulas){
		pessoasCelulas = pPessoasCelulas;
	}

	public List<CelulaReuniao> getReunioes(){
		return reunioes;
	}

	public void setReunioes(List<CelulaReuniao> pReunioes){
		reunioes = pReunioes;
	}

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}

	public boolean isAtiva(){
		return ativa;
	}

	public void setAtiva(boolean pAtiva){
		ativa = pAtiva;
	}

	public boolean isComVisitante(){
		return comVisitante;
	}

	public void setComVisitante(boolean pComVisitante){
		comVisitante = pComVisitante;
	}

	public boolean isComEnderecoFixo(){
		return comEnderecoFixo;
	}

	public void setComEnderecoFixo(boolean pComEnderecoFixo){
		comEnderecoFixo = pComEnderecoFixo;
	}
}
