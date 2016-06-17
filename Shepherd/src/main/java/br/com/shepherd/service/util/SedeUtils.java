package br.com.shepherd.service.util;

import java.util.ArrayList;
import java.util.List;

import br.com.shepherd.entity.Email;
import br.com.shepherd.entity.Sede;
import br.com.shepherd.entity.Telefone;

public class SedeUtils{
	EnderecoUtils	enderecoUtils = new EnderecoUtils();
	EmailUtils		emailUtils = new EmailUtils();
	TelefoneUtils	telefoneUtils = new TelefoneUtils();

	public Sede consistir(Sede pSede) throws Exception{
		if(!pSede.getNome().isEmpty()){
			pSede.setNome(pSede.getNome().trim());
		} else{
			throw new Exception("O cadastro possui campos obrigatórios não preenchidos!");
		}

		if(!pSede.getCnpj().isEmpty()){
			pSede.setCnpj(pSede.getCnpj().trim());
		} else{
			pSede.setCnpj(null);
		}

		if(!pSede.getPaginaWeb().isEmpty()){
			pSede.setPaginaWeb(pSede.getPaginaWeb().trim());
		} else{
			pSede.setPaginaWeb(null);
		}

		if(!pSede.getPerfilRedeSocial().isEmpty()){
			pSede.setPerfilRedeSocial(pSede.getPerfilRedeSocial().trim());
		} else{
			pSede.setPerfilRedeSocial(null);
		}

		if(pSede.isMae()){
			pSede.setSedeMae(null);
		} else{
			if(pSede.getSedeMae().getNome().isEmpty()){
				// Sede filha não possui Sede Mãe
				throw new Exception("Nenhuma Sede-Mãe selecionada! Obrigatório para Sede-Filha!");
			}
		}

		// Consistir objeto Endereço
		pSede.setEndereco(enderecoUtils.consistir(pSede.getEndereco()));

		if(enderecoUtils.isEmpty(pSede.getEndereco())){

			throw new Exception("O cadastro possui campos obrigatórios não preenchidos!");
		}

		// Consistir lista de Emails
		List<Email> tEmails = new ArrayList<Email>();

		for(Email pEmail : pSede.getEmails()){
			pEmail = emailUtils.consistir(pEmail);

			if(emailUtils.isEmpty(pEmail)){
				pSede.getEmails().remove(pEmail);
			} else{
				tEmails.add(pEmail);
			}
		}

		if(!tEmails.isEmpty()){
			pSede.setEmails(tEmails);
			tEmails = new ArrayList<Email>();
		}

		if(pSede.getEmails().isEmpty()){
		throw new Exception("E-mail! Cadastre pelo menos um endereço!");
		}

		// Consistir lista de Telefones
		List<Telefone> tTelefones = new ArrayList<Telefone>();

		for(Telefone pTelefone : pSede.getTelefones()){
			pTelefone = telefoneUtils.consistir(pTelefone);

			if(telefoneUtils.isEmpty(pTelefone)){
				pSede.getTelefones().remove(pTelefone);
			} else{
				tTelefones.add(pTelefone);
			}
		}

		if(!tTelefones.isEmpty()){
			pSede.setTelefones(tTelefones);
			tTelefones = new ArrayList<Telefone>();
		}

		if(pSede.getTelefones().isEmpty()){
		throw new Exception("Telefone! Cadastre pelo menos um contato!");
		}

		if(pSede.getComentarios().isEmpty()){
			pSede.setComentarios(null);
		} else{
			pSede.setComentarios(pSede.getComentarios().trim());
		}

		return pSede;
	}
}
