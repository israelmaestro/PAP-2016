package br.com.shepherd.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Endereco implements Serializable{
	private static final long	serialVersionUID	= 484952411064066577L;

	@Id
	@GeneratedValue
	private Integer				id;

	@OneToOne
	private Celula				celula;

	@OneToOne
	private Sede				sede;

	@OneToOne
	private Pessoa				pessoa;

	@OneToOne
	private CelulaReuniao		celulaReuniao;

	@OneToOne
	private Atendimento			atendimento;

	private String				descricao;

	@NotNull
	private boolean				gps					= false;

	@Column(length = 8)
	private String				cep;

	private String				logradouro;

	@Column(length = 5)
	private Integer				numero;

	private String				complemento;

	private String				bairro;

	private String				cidade;

	@Column(length = 2)
	private String				estado;

	private String				pais;

	private String				coordenadas;

	public Endereco(){
	}

	/**
	 * Consiste os atributos do endereço
	 *
	 * @throws Exception
	 */
	public void consistir() throws Exception{
		if(gps){
			if(null == coordenadas || coordenadas.equals("")){
				// Campo de coordenadas vazio
				throw new Exception("Coordenadas: Campo obrigatório quando o endereço é do tipo GPS.");
			} else if(!coordenadas.matches("([+-]?\\d+\\.?\\d+)\\s*,\\s*([+-]?\\d+\\.?\\d+)")){
				// Campo de coordenadas incorreto
				throw new Exception("Coordenadas: Preencha corretamente o campo (±9.9*, ±9.9*).");
			} else{
				coordenadas = coordenadas.trim();
				logradouro = null;
				numero = null;
				complemento = null;
				bairro = null;
				cidade = null;
				estado = null;
				pais = null;
			}
		} else{
			if(null != logradouro || !logradouro.equals("")){
				logradouro = logradouro.trim();

				if(null == numero || numero.equals("")){
					// Rua sem número
					throw new Exception("Endereço incompleto! Preencha o campo “№”.");
				}

				if(null == cidade || cidade.equals("")){
					// Rua sem cidade
					throw new Exception("Endereço incompleto! Preencha o campo “Cidade”.");
				} else{
					cidade = cidade.trim();
				}
			} else{
				logradouro = null;

				numero = null;

				complemento = null;

				if(null == cidade || cidade.equals("")){
					cidade = null;
				} else{
					cidade = cidade.trim();
				}
			}

			if(null == complemento || complemento.equals("")){
				complemento = null;
			} else{
				complemento = complemento.trim();
			}

			if(null == estado || estado.equals("")){
				complemento = null;
			} else{
				estado = estado.trim();
			}

			if(null == cep || cep.equals("")){
				cep = null;
			} else{
				cep = cep.trim();
			}

			if(null == pais || pais.equals("")){
				pais = null;
			} else{
				pais = pais.trim();
			}
		}
	}

	/**
	 * Verifica se o endereço está vazio
	 *
	 * @return
	 */
	public boolean isEmpty(){
		if((null == logradouro || logradouro.equals(""))&& (null == numero || numero.equals(""))
			&& (null == complemento || complemento.equals(""))
			&& (null == bairro || bairro.equals(""))
			&& (null == cidade || cidade.equals(""))
			&& (null == estado || estado.equals(""))
			&& (null == cep || cep.equals(""))
			&& (null == pais || pais.equals(""))){
			if(isGps()){
				if(null == coordenadas || coordenadas.equals("")){
					return true;
				} else{
					return false;
				}
			} else{
				return true;
			}
		} else{
			return false;
		}
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
		Endereco other = (Endereco) obj;
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

	public Celula getCelula(){
		return celula;
	}

	public void setCelula(Celula pCelula){
		celula = pCelula;
	}

	public Sede getSede(){
		return sede;
	}

	public void setSede(Sede pSede){
		sede = pSede;
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public CelulaReuniao getCelulaReuniao(){
		return celulaReuniao;
	}

	public void setCelulaReuniao(CelulaReuniao pCelulaReuniao){
		celulaReuniao = pCelulaReuniao;
	}

	public Atendimento getAtendimento(){
		return atendimento;
	}

	public void setAtendimento(Atendimento pAtendimento){
		atendimento = pAtendimento;
	}

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String pDescricao){
		descricao = pDescricao;
	}

	public boolean isGps(){
		return gps;
	}

	public void setGps(boolean pGps){
		gps = pGps;
	}

	public String getCep(){
		return cep;
	}

	public void setCep(String pCep){
		cep = pCep;
	}

	public String getLogradouro(){
		return logradouro;
	}

	public void setLogradouro(String pLogradouro){
		logradouro = pLogradouro;
	}

	public Integer getNumero(){
		return numero;
	}

	public void setNumero(Integer pNumero){
		numero = pNumero;
	}

	public String getComplemento(){
		return complemento;
	}

	public void setComplemento(String pComplemento){
		complemento = pComplemento;
	}

	public String getBairro(){
		return bairro;
	}

	public void setBairro(String pBairro){
		bairro = pBairro;
	}

	public String getCidade(){
		return cidade;
	}

	public void setCidade(String pCidade){
		cidade = pCidade;
	}

	public String getEstado(){
		return estado;
	}

	public void setEstado(String pEstado){
		estado = pEstado;
	}

	public String getPais(){
		return pais;
	}

	public void setPais(String pPais){
		pais = pPais;
	}

	public String getCoordenadas(){
		return coordenadas;
	}

	public void setCoordenadas(String pCoordenadas){
		coordenadas = pCoordenadas;
	}
}
