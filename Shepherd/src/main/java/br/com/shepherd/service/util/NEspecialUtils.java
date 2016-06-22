package br.com.shepherd.service.util;

import br.com.shepherd.entity.NEspecial;

public class NEspecialUtils{

	/**
	 * Consiste os campos de NEspecial
	 *
	 * @param pNEspecial
	 * @return pNEspecial (consistido
	 * @throws Exception
	 */
	public NEspecial consistir(NEspecial pNEspecial) throws Exception{
		if(null != pNEspecial.getNome()){
			if(!pNEspecial.getNome().isEmpty()){
				pNEspecial.setNome(pNEspecial.getNome().trim());
			} else{
				throw new Exception("Nome: Campo obrigatório!");
			}
		} else{
			throw new Exception("Nome: Campo obrigatório!");
		}

		if(null != pNEspecial.getTipo()){
			if(!pNEspecial.getTipo().isEmpty()){
				pNEspecial.setTipo(pNEspecial.getTipo().trim());
			} else{
				pNEspecial.setTipo(null);
			}
		}

		if(null != pNEspecial.getDescricao()){
			if(!pNEspecial.getDescricao().isEmpty()){
				pNEspecial.setDescricao(pNEspecial.getDescricao().trim());
			} else{
				pNEspecial.setDescricao(null);
			}
		}

		return pNEspecial;
	}

	/**
	 * Verifica se os campos de NEspecial estão vazios
	 *
	 * @param pNEspecial
	 * @return true (sim) / false (não)
	 */
	public boolean isEmpty(NEspecial pNEspecial){
		if((null == pNEspecial.getNome() || pNEspecial.getNome().isEmpty())
			&& (null == pNEspecial.getTipo() || pNEspecial.getTipo().isEmpty())
			&& (null == pNEspecial.getDescricao() || pNEspecial.getDescricao().isEmpty())){
			return true;
		} else{
			return false;
		}
	}
}
