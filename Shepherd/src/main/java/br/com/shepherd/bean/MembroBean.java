package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import br.com.shepherd.entity.Coordenador;
import br.com.shepherd.entity.Email;
import br.com.shepherd.entity.Lider;
import br.com.shepherd.entity.Ministro;
import br.com.shepherd.entity.Pessoa;
import br.com.shepherd.entity.Telefone;
import br.com.shepherd.service.MembroService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@SessionScoped
public class MembroBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{membroService}")
	private MembroService		membroService;

	private Pessoa				pessoa;

	public MembroBean(){
		pessoa = new Pessoa();
	}

	public void botaoRemoverTelefone(Telefone pTelefone){
		pessoa.removeTelefone(pTelefone);
	}

	public void botaoAdicionarTelefone(AjaxBehaviorEvent pAjax){
		pessoa.addTelefone(new Telefone());
	}

	public void botaoRemoverEmail(Email pEmail){
		pessoa.removeEmail(pEmail);
	}

	public void botaoAdicionarEmail(AjaxBehaviorEvent pAjax){
		pessoa.addEmail(new Email());
	}

	public String cadastrar(){
		try{
			membroService.cadastrar(pessoa);

			JSFUtil.addInfoMessage("Membro �"+ pessoa.getNome() + " " + pessoa.getSobrenome()
									+ "� cadastrado com sucesso!");

			pessoa = new Pessoa();

		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
		}

		return "membroCadastrar";
	}

	public void alterar(Pessoa pPessoa){
		membroService.alterar(pPessoa);

		JSFUtil.addInfoMessage("Membro �"+ pessoa.getNome() + " " + pessoa.getSobrenome()
								+ "� alterado com sucesso.");
	}

	public List<Pessoa> listar(){
		return membroService.listar();
	}

	public List<Pessoa> listarExcluindo(Pessoa pPai, Pessoa pMae){
		return membroService.listarExcluindo(pPai, pMae);
	}

	public List<Pessoa> listarHomens(){
		return membroService.listarHomens();
	}

	public List<Pessoa> listarHomensSolteiros(){
		return membroService.listarHomensSolteiros();
	}

	public List<Pessoa> listarMulheres(){
		return membroService.listarMulheres();
	}

	public List<Pessoa> listarMulheresSolteiras(){
		return membroService.listarMulheresSolteiras();
	}

	public List<Lider> listarLideres(){
		return membroService.listarLideres();
	}

	public List<Coordenador> listarCoordenadores(){
		return membroService.listarCoordenadores();
	}

	public List<Ministro> listarPresidentes(){
		return membroService.listarPresidentes();
	}

	public void excluir(Pessoa pPessoa){
		membroService.excluir(pPessoa);

		JSFUtil.addInfoMessage("Membro �"+ pessoa.getNome() + " " + pessoa.getSobrenome()
								+ "� excu�do com sucesso.");
	}

	public Pessoa getMembro(){
		return pessoa;
	}

	public void setMembro(Pessoa pMembro){
		pessoa = pMembro;
	}
}
