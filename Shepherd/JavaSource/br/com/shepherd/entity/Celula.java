package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {
								@UniqueConstraint(columnNames = {
																	"nome"
								})
})
public class Celula implements Serializable{
	private static final long	serialVersionUID	= -7390302449224224794L;

	@Id
	@GeneratedValue
	private Integer				id;

	// Dados da célula
	@NotNull
	private String				nome;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "celula")
	private List<Membro>		membros;

	@NotNull
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "celula")
	private List<Lider>			lideres;

	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Coordenador			coordenador;

	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Frente				frente;

	public Celula(){
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

	public List<Membro> getMembros(){
		return membros;
	}

	public void setMembros(List<Membro> pMembros){
		membros = pMembros;
	}

	public List<Lider> getLideres(){
		return lideres;
	}

	public void setLideres(List<Lider> pLideres){
		lideres = pLideres;
	}

	public Coordenador getCoordenador(){
		return coordenador;
	}

	public void setCoordenador(Coordenador pCoordenador){
		coordenador = pCoordenador;
	}

	public Frente getFrente(){
		return frente;
	}

	public void setFrente(Frente pFrente){
		frente = pFrente;
	}
}
