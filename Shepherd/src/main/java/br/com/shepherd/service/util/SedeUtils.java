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
			throw new Exception("O cadastro possui campos obrigat�rios n�o preenchidos!");
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
				// Sede filha n�o possui Sede M�e
				throw new Exception("Nenhuma Sede-M�e selecionada! Obrigat�rio para Sede-Filha!");
			}
		}

		// Consistir objeto Endere�o
		pSede.setEndereco(enderecoUtils.consistir(pSede.getEndereco()));

		if(enderecoUtils.isEmpty(pSede.getEndereco())){
			throw new Exception("O cadastro possui campos obrigat�rios n�o preenchidos!");
		}

		// Consistir lista de Telefones
		if(null != pSede.getTelefones()){
			if(!pSede.getTelefones().isEmpty()){
				List<Telefone> tTelefones = new ArrayList<Telefone>();
				for(Telefone pTelefone : pSede.getTelefones()){
					pTelefone = telefoneUtils.consistir(pTelefone);

					if(null == pTelefone || telefoneUtils.isEmpty(pTelefone)){
						pSede.getTelefones().remove(pTelefone);
					} else{
						tTelefones.add(pTelefone);
					}
				}
				if(!tTelefones.isEmpty()){
					pSede.setTelefones(tTelefones);
					tTelefones = new ArrayList<Telefone>();
				} else{
					throw new Exception("O cadastro precisa ter pelo menos 1 telefone.");
				}
			} else{
				throw new Exception("O cadastro precisa ter pelo menos 1 telefone.");
			}
		} else{
			throw new Exception("O cadastro precisa ter pelo menos 1 telefone.");
		}

		// Consistir lista de Emails
		if(null != pSede.getEmails()){
			if(!pSede.getEmails().isEmpty()){
				List<Email> tEmails = new ArrayList<Email>();
				for(Email pEmail : pSede.getEmails()){
					pEmail = emailUtils.consistir(pEmail);

					if(null == pEmail || emailUtils.isEmpty(pEmail)){
						pSede.getEmails().remove(pEmail);
					} else{
						tEmails.add(pEmail);
					}
				}
				if(!tEmails.isEmpty()){
					pSede.setEmails(tEmails);
					tEmails = new ArrayList<Email>();
				} else{
					throw new Exception("O cadastro precisa ter pelo menos 1 email.");
				}
			} else{
				throw new Exception("O cadastro precisa ter pelo menos 1 email.");
			}
		} else{
			throw new Exception("O cadastro precisa ter pelo menos 1 email.");
		}

		return pSede;
	}
}
