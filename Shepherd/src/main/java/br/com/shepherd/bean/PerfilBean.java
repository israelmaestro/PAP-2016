package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.shepherd.entity.Perfil;
import br.com.shepherd.service.PerfilService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@ViewScoped
public class PerfilBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	private PerfilService		perfilService;

	private Perfil				perfil;

	public PerfilBean(){
		setPerfil(new Perfil());
	}

	public String cadastrar(){
		try{
			perfilService.cadastrar(perfil);
			perfil = new Perfil();

			JSFUtil.addInfoMessage("Cadastro de Perfil realizado com sucesso!");

			return "perfilListar";
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
			return "perfilCadastrar";
		}
	}

	public void alterar(Perfil pPerfil){
		perfilService.alterar(pPerfil);

		JSFUtil.addInfoMessage("Perfil alterado com sucesso.");
	}

	public List<Perfil> listar(){
		return perfilService.listar();
	}

	public void excluir(Perfil pPerfil){
		perfilService.excluir(pPerfil);

		JSFUtil.addInfoMessage("Perfil excuído com sucesso.");
	}

	public Perfil getPerfil(){
		return perfil;
	}

	public void setPerfil(Perfil pPerfil){
		perfil = pPerfil;
	}
}
