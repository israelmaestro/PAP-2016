package br.com.shepherd.service.util;

import br.com.shepherd.entity.Email;
import br.com.shepherd.entity.Pessoa;
import br.com.shepherd.entity.Telefone;

public class PessoaUtils{
	EnderecoUtils	enderecoUtils= new EnderecoUtils();
	EmailUtils		emailUtils = new EmailUtils();
	TelefoneUtils	telefoneUtils = new TelefoneUtils();

	/**
	 * Consiste os campos de Pessoa
	 *
	 * @throws Exception
	 * @return Pessoa
	 */
	public Pessoa consistir(Pessoa pPessoa) throws Exception{
		if(null != pPessoa.getNome() || !pPessoa.getNome().equals("")){
			pPessoa.setNome(pPessoa.getNome().trim());
		} else{
			throw new Exception("Nome: Campo obrigatório!");
		}

		if(null != pPessoa.getSobrenome() || !pPessoa.getSobrenome().equals("")){
			pPessoa.setSobrenome(pPessoa.getSobrenome().trim());
		} else{
			throw new Exception("Sobrenome: Campo obrigatório!");
		}

		if(null != pPessoa.getRg() || !pPessoa.getRg().equals("")){
			pPessoa.setRg(pPessoa.getRg().trim());
		} else{
			pPessoa.setRg(null);
		}

		if(null != pPessoa.getCpf() || !pPessoa.getCpf().equals("")){
			pPessoa.setCpf(pPessoa.getCpf().trim());
		} else{
			pPessoa.setCpf(null);
		}

		if(pPessoa.isBatizada()){
			if(null != pPessoa.getDataBatismo() || pPessoa.getDataBatismo().equals("")){
				pPessoa.setDataBatismo(null);
			}
		}

		if(null != pPessoa.getComentarios() || !pPessoa.getComentarios().equals("")){
			pPessoa.setComentarios(pPessoa.getComentarios().trim());
		} else{
			pPessoa.setComentarios(null);
		}

		// Consistir objeto Endereço
		pPessoa.setEndereco(enderecoUtils.consistir(pPessoa.getEndereco()));

		if(enderecoUtils.isEmpty(pPessoa.getEndereco())){
			pPessoa.setEndereco(null);
		}

		// Consistir lista de Emails
		for(Email pEmail : pPessoa.getEmails()){
			pEmail = emailUtils.consistir(pEmail);

			if(emailUtils.isEmpty(pEmail)){
				pPessoa.getEmails().remove(pEmail);
			}
		}

		if(pPessoa.getEmails().isEmpty()){
			pPessoa.setEmails(null);
		}

		// Consistir lista de Telefones
		for(Telefone pTelefone : pPessoa.getTelefones()){
			pTelefone = telefoneUtils.consistir(pTelefone);

			if(telefoneUtils.isEmpty(pTelefone)){
				pPessoa.getTelefones().remove(pTelefone);
			}
		}

		if(pPessoa.getTelefones().isEmpty()){
			pPessoa.setTelefones(null);
		}

		return pPessoa;
	}
}
