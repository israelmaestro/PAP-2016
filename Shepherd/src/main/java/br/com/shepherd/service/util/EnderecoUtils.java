package br.com.shepherd.service.util;

import br.com.shepherd.entity.Endereco;

public class EnderecoUtils{

	/**
	 * Consiste os atributos do endereço
	 *
	 * @throws Exception
	 */
	public Endereco consistir(Endereco pEndereco) throws Exception{
		if(pEndereco.isGps()){
			if(pEndereco.getCoordenadas().isEmpty()){
				// Campo de coordenadas vazio
				throw new Exception("Coordenadas: Campo obrigatório quando o endereço é do tipo GPS.");
			} else if(!pEndereco.getCoordenadas()
								.matches("([+-]?\\d+\\.?\\d+)\\s*,\\s*([+-]?\\d+\\.?\\d+)")){
				// Campo de coordenadas incorreto
				throw new Exception("Coordenadas: Preencha corretamente o campo (±9.9*, ±9.9*).");
			} else{
				pEndereco.setCoordenadas(pEndereco.getCoordenadas().trim());
				pEndereco.setLogradouro(null);
				pEndereco.setNumero(null);
				pEndereco.setComplemento(null);
				pEndereco.setBairro(null);
				pEndereco.setCidade(null);
				pEndereco.setEstado(null);
				pEndereco.setPais(null);
			}
		} else{
			if(!pEndereco.getLogradouro().isEmpty()){
				pEndereco.setLogradouro(pEndereco.getLogradouro().trim());

				if(null == pEndereco.getNumero()){
					// Rua sem número
					throw new Exception("Endereço incompleto! Preencha o campo “№”.");
				}

				if(pEndereco.getCidade().isEmpty()){
					// Rua sem cidade
					throw new Exception("Endereço incompleto! Preencha o campo “Cidade”.");
				} else{
					pEndereco.setCidade(pEndereco.getCidade().trim());
				}
			} else{
				pEndereco.setLogradouro(null);

				pEndereco.setNumero(null);

				pEndereco.setComplemento(null);

				if(pEndereco.getCidade().isEmpty()){
					pEndereco.setCidade(null);
				} else{
					pEndereco.setCidade(pEndereco.getCidade().trim());
				}
			}

			if(!pEndereco.getComplemento().isEmpty()){
				pEndereco.setComplemento(pEndereco.getComplemento().trim());
			} else{
				pEndereco.setComplemento(null);
			}

			if(!pEndereco.getBairro().isEmpty()){
				pEndereco.setBairro(pEndereco.getBairro().trim());
			} else{
				pEndereco.setBairro(null);
			}

			if(!pEndereco.getEstado().isEmpty()){
				pEndereco.setEstado(pEndereco.getEstado().trim());
			} else{
				pEndereco.setEstado(null);
			}

			if(!pEndereco.getCep().isEmpty()){
				pEndereco.setCep(pEndereco.getCep().trim());
			} else{
				pEndereco.setCep(null);
			}

			if(!pEndereco.getPais().isEmpty()){
				pEndereco.setPais(pEndereco.getPais().trim());
			} else{
				pEndereco.setPais(null);
			}
		}

		return pEndereco;
	}

	/**
	 * Verifica se o endereço está vazio
	 *
	 * @return true (vazio) / false (não-vazio)
	 */
	public boolean isEmpty(Endereco pEndereco){
		if(pEndereco.getLogradouro().isEmpty()//
			&& null == pEndereco.getNumero()
			&& pEndereco.getComplemento().isEmpty()
			&& pEndereco.getBairro().isEmpty()
			&& pEndereco.getCidade().isEmpty()
			&& pEndereco.getEstado().isEmpty()
			&& pEndereco.getCep().isEmpty()
			&& pEndereco.getPais().isEmpty()){
			if(pEndereco.isGps()){
				if(pEndereco.getCoordenadas().isEmpty()){
					return true;
				} else{
					return false;
				}
			} else{
				pEndereco.setCoordenadas(null);
				return true;
			}
		} else{
			return false;
		}
	}
}
