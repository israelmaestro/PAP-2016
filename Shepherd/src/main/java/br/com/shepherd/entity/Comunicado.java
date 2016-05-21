/*
 * TODO: Os comunicados devem receber automaticamente os valores de sede e
 * ministro, baseando-se no usuário logado
 */
package br.com.shepherd.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "codigo" }) })
public class Comunicado implements Serializable{
	private static final long	serialVersionUID	= -1189982959606136016L;

	@Id
	@NotNull
	private String				codigo				= "";

	@NotNull
	@NotEmpty
	@Column(length = 10000)
	private String				conteudo;

	/*
	 * TODO: Habilitar "@NotNull" na sede após implementação de Membro..MINISTRO
	 * e USUARIO
	 */
	// @NotNull
	@OneToOne // (fetch = FetchType.EAGER)
	private Sede				sede;

	/*
	 * TODO: Habilitar "@NotNull" em ministro após implementação de
	 * Membro..MINISTRO e USUARIO
	 */
	// @NotNull
	@OneToOne // (fetch = FetchType.EAGER)
	private Usuario				autor;

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + (codigo == null ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){ return true; }
		if(obj == null){ return false; }
		if(getClass() != obj.getClass()){
			return false;
		}
		Comunicado other = (Comunicado) obj;
		if(codigo == null){
			if(other.codigo != null){ return false; }
		} else if(!codigo.equals(other.codigo)){
			return false;
		}
		return true;
	}

	// Construtor e afins
	public Comunicado(){
	}

	public String getCodigo(){
		return codigo;
	}

	public void setCodigo(String pCodigo){
		codigo = pCodigo;
	}

	public String getConteudo(){
		return conteudo;
	}

	public void setConteudo(String pConteudo){
		conteudo = pConteudo;
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}

	public Usuario getAutor(){
		return autor;
	}

	public void setAutor(Usuario pAutor){
		autor = pAutor;
	}

}
