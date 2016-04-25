package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {
								@UniqueConstraint(columnNames = {
																	"nome"
								})
})
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

	// TODO: Devolver a anotação quando o membro for implementado.
	// @NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Membro>		lideres;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "frentesParticipadas")
	private List<Membro>		participantes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "frente")
	private List<Celula>		celulas;

	// TODO: Refazer o formulário para ficar com os mesmos padrões que o
	// formulário de Sede, incluindo os campo novos

	private Integer				idadeMinima;

	private Integer				idadeMaxima;

	// Flags
	@NotNull
	private boolean				isCell				= false;

	// Construtor e afins
	public Frente(){
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

	public List<Membro> getLideres(){
		return lideres;
	}

	public void setLideres(List<Membro> pLideres){
		lideres = pLideres;
	}

	public List<Membro> getParticipantes(){
		return participantes;
	}

	public void setParticipantes(List<Membro> pParticipantes){
		participantes = pParticipantes;
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
}
