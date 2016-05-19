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

	private String				confirmacaoSenha;

	private Usuario 			usuarioTemp;

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

	public String cadastrar() throws Exception{
		try{

			if(confirmacaoSenha.equals(usuario.getSenha())){

			usuarioService.cadastrar(usuario);
			usuario = new Usuario();

			JSFUtil.addInfoMessage("Cadastro de Usuário realizado com sucesso.");

				return "usuarioListar";
			} else{
				JSFUtil.addErrorMessage("A senha e a sua confirmação devem ser iguais!");
				return "usuarioCadastrar";
			}
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
			return "usuarioCadastrar";
		}
	}

	public String prepararAlteracao(Usuario pUsuario){

		setUsuarioTemp(pUsuario);

		return "usuarioAlterar";
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

	public String getConfirmacaoSenha(){
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String pConfirmacaoSenha){
		confirmacaoSenha = pConfirmacaoSenha;
	}

	public Usuario getUsuarioTemp() {
		return usuarioTemp;
	}

	public void setUsuarioTemp(Usuario pUsuarioTemp) {
		usuarioTemp = pUsuarioTemp;
	}
}
