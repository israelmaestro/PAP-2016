package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.shepherd.entity.Usuario;
import br.com.shepherd.service.UsuarioService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	private UsuarioService		usuarioService;

	private Usuario				usuario;

	public UsuarioBean(){
		usuario = new Usuario();
	}

	public String login(){
		try{
			usuarioService.login(usuario);
			return "home";
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}
		return "index";
	}

	public void cadastrar() throws Exception{
		try{
			usuarioService.cadastrar(usuario);
			usuario = new Usuario();

			JSFUtil.addInfoMessage("Cadastro de Usuário realizado com sucesso.");
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}
	}

	public void alterar(Usuario pUsuario){
		usuarioService.alterar(pUsuario);

		JSFUtil.addInfoMessage("Usuário alterado com sucesso.");
	}

	public List<Usuario> listar(){
		return usuarioService.listar();
	}

	public void excluir(Usuario pUsuario){
		usuarioService.excluir(pUsuario);

		JSFUtil.addInfoMessage("Usuário excuído com sucesso.");
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public void setUsuario(Usuario pUsuario){
		usuario = pUsuario;
	}
}
