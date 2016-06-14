package br.com.shepherd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "celula_reuniao")
public class CelulaReuniao implements Serializable{
	private static final long	serialVersionUID	= -7390302449224224794L;

	// Id =
	@Id
	private String				id;

	// Dados da Reunião
	@Column(columnDefinition = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dataHora;

	private String				titulo;

	@OneToOne
	private Celula				celula;

	@OneToOne(mappedBy = "celulaReuniao", cascade = CascadeType.ALL)
	private Endereco			endereco;

	@OneToMany(mappedBy = "celulaReuniao", cascade = CascadeType.ALL)
	private List<RegistroPresenca>	presencas;

	@Column(length=1000)
	private String				comentarios;

	public CelulaReuniao(){
		setPresencas(new ArrayList<RegistroPresenca>());
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
		CelulaReuniao other = (CelulaReuniao) obj;
		if(id == null){
			if(other.id != null){ return false; }
		} else if(!id.equals(other.id)){ return false; }
		return true;
	}

	public String getId(){
		return id;
	}

	public void setId(String pId){
		id = pId;
	}

	public Date getDataHora(){
		return dataHora;
	}

	public void setDataHora(Date pDataHora){
		dataHora = pDataHora;
	}

	public String getTitulo(){
		return titulo;
	}

	public void setTitulo(String pTitulo){
		titulo = pTitulo;
	}

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
	}

	public Endereco getEndereco(){
		return endereco;
	}

	public void setEndereco(Endereco pEndereco){
		endereco = pEndereco;
	}

	public List<RegistroPresenca> getPresencas(){
		return presencas;
	}

	public void setPresencas(List<RegistroPresenca> pPresencas){
		presencas = pPresencas;
	}

	public String getComentarios(){
		return comentarios;
	}

	public void setComentarios(String pComentarios){
		comentarios = pComentarios;
	}
}
