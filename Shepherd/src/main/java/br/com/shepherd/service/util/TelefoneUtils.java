package br.com.shepherd.service.util;

import br.com.shepherd.entity.Telefone;

public class TelefoneUtils{
	/**
	 * Consiste os campos de Telefone
	 *
	 * @return Telefone
	 */
	public Telefone consistir(Telefone pTelefone){
		if(null != pTelefone){
			if(!pTelefone.getNumero().isEmpty()){
				pTelefone.setNumero(pTelefone.getNumero().trim());
				
				if(!pTelefone.getDescricao().isEmpty()){
					pTelefone.setDescricao(pTelefone.getDescricao().trim());
				}
			} else{
				pTelefone.setDescricao(null);
			}
		}

		return pTelefone;
	}

	/**
	 * Verifica se todos os campos de Telefone estão vazios
	 *
	 * @return true (vazio) / false (não-vazio)
	 */
	public boolean isEmpty(Telefone pTelefone){
		if(pTelefone.getNumero().isEmpty() && pTelefone.getDescricao().isEmpty()){
			return true;
		} else{
			return false;
		}
	}
}
