package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.shepherd.entity.NEspecial;
import br.com.shepherd.service.NEspecialService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@RequestScoped
public class NEspecialBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	private NEspecialService	nEspecialService;

	private NEspecial			nEspecial;

	public NEspecialBean(){
		nEspecial = new NEspecial();
	}

	public void cadastrar(){
		try{
			nEspecialService.cadastrar(nEspecial);
			nEspecial = new NEspecial();

			JSFUtil.addInfoMessage("Cadastro de Necessiade Especial realizado com sucesso!");
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}
	}

	public void alterar(NEspecial pNEspecial){
		nEspecialService.alterar(pNEspecial);

		JSFUtil.addInfoMessage("Necessidade Especial alterada com sucesso.");
	}

	public List<NEspecial> listar(){
		return nEspecialService.listar();
	}

	public void excluir(NEspecial pNEspecial){
		nEspecialService.excluir(pNEspecial);

		JSFUtil.addInfoMessage("Necessidade Especial excuída com sucesso.");
	}

	public NEspecial getNEspecial(){
		return nEspecial;
	}

	public void setNEspecial(NEspecial pNEspecial){
		nEspecial = pNEspecial;
	}
}
