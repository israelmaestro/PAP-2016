package br.com.shepherd.service.util;

import java.util.ArrayList;
import java.util.List;

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
		if(!pPessoa.getNome().isEmpty()){
			pPessoa.setNome(pPessoa.getNome().trim());
		} else{
			throw new Exception("Nome: Campo obrigatório!");
		}

		if(!pPessoa.getSobrenome().isEmpty()){
			pPessoa.setSobrenome(pPessoa.getSobrenome().trim());
		} else{
			throw new Exception("Sobrenome: Campo obrigatório!");
		}

		if(!pPessoa.getRg().isEmpty()){
			pPessoa.setRg(pPessoa.getRg().trim());
		} else{
			pPessoa.setRg(null);
		}

		if(!pPessoa.getCpf().isEmpty()){
			pPessoa.setCpf(pPessoa.getCpf().trim());
		} else{
			pPessoa.setCpf(null);
		}

		if(!pPessoa.getComentarios().isEmpty()){
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
		List<Email> tEmails = new ArrayList<Email>();
		for(Email pEmail : pPessoa.getEmails()){
			pEmail = emailUtils.consistir(pEmail);

			if(emailUtils.isEmpty(pEmail)){
				pPessoa.getEmails().remove(pEmail);
			} else{
				tEmails.add(pEmail);
			}
		}

		if(!tEmails.isEmpty()){
			pPessoa.setEmails(tEmails);
			tEmails = new ArrayList<Email>();
		}

		if(pPessoa.getEmails().isEmpty()){
			pPessoa.setEmails(null);
		}

		// Consistir lista de Telefones
		List<Telefone> tTelefones = new ArrayList<Telefone>();

		for(Telefone pTelefone : pPessoa.getTelefones()){
			pTelefone = telefoneUtils.consistir(pTelefone);

			if(telefoneUtils.isEmpty(pTelefone)){
				pPessoa.getTelefones().remove(pTelefone);
			} else{
				tTelefones.add(pTelefone);
			}
		}

		if(!tTelefones.isEmpty()){
			pPessoa.setTelefones(tTelefones);
			tTelefones = new ArrayList<Telefone>();
		}

		if(pPessoa.getTelefones().isEmpty()){
			pPessoa.setTelefones(null);
		}

		return pPessoa;
	}
}
