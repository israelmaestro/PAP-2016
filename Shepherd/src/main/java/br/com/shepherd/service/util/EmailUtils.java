package br.com.shepherd.service.util;

import br.com.shepherd.entity.Email;

public class EmailUtils{
	/**
	 * Consiste os campos de Email
	 */
	public Email consistir(Email pEmail){
		if(!pEmail.getConta().isEmpty()){
			pEmail.setConta(pEmail.getConta().trim());
		}

		if(!pEmail.getDescricao().isEmpty()){
			pEmail.setDescricao(pEmail.getDescricao().trim());
		}

		return pEmail;
	}

	/**
	 * Verifica se todos os campos de Email estão vazios
	 *
	 * @return true (vazio) / false (não-vazio)
	 */
	public boolean isEmpty(Email pEmail){
		if(pEmail.getConta().isEmpty() && pEmail.getDescricao().isEmpty()){
			return true;
		} else{
			return false;
		}
	}
}
