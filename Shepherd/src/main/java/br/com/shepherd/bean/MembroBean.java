package br.com.shepherd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import br.com.shepherd.entity.Email;
import br.com.shepherd.entity.Pessoa;
import br.com.shepherd.entity.PessoaCelula;
import br.com.shepherd.entity.Telefone;
import br.com.shepherd.service.MembroService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@SessionScoped
public class MembroBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{pessoaService}")
	private MembroService		membroService;

	private Pessoa				membro;

	public MembroBean() throws IOException{
		membro = new Pessoa();
		membro.addRelacionamentoCelula(new PessoaCelula(), JSFUtil.getProperty("funcaoMembro"));
	}

	public void addCelula(AjaxBehaviorEvent pAjax) throws IOException{
		membro.addRelacionamentoCelula(new PessoaCelula(), JSFUtil.getProperty("funcaoMembro"));
	}

	public void botaoAdicionarTelefone(AjaxBehaviorEvent pAjax){
		membro.addTelefone(new Telefone());
	}

	public void botaoRemoverTelefone(Telefone pTelefone){
		membro.removeTelefone(pTelefone);
	}

	public void botaoAdicionarEmail(AjaxBehaviorEvent pAjax){
		membro.addEmail(new Email());
	}

	public void botaoRemoverEmail(Email pEmail){
		membro.removeEmail(pEmail);
	}

	public String cadastrarMembro(){
		try{

			membroService.cadastrarMembro(membro);

			JSFUtil.addInfoMessage("Membro “"+ membro.getNome()
									+ " "
									+ membro.getSobrenome()
									+ "” cadastrado com sucesso!");

			membro = new Pessoa();

			membro.addRelacionamentoCelula(new PessoaCelula(), JSFUtil.getProperty("funcaoMembro"));

			return "membroListar";
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
			return "membroCadastrar";
		}
	}

	public void alterarMembro(Pessoa pPessoa){
		membroService.alterarMembro(pPessoa);

		JSFUtil.addInfoMessage("Membro “"+ membro.getNome()
								+ " "
								+ membro.getSobrenome()
								+ "” alterado com sucesso.");
	}

	public List<PessoaCelula> listarMembros(){
		return membroService.listarMembros();
	}

	public List<Pessoa> listar(){
		return membroService.listar();
	}

	// public List<Pessoa> listarExcluindo(Pessoa pPai, Pessoa pMae){
	// return pessoaService.listarExcluindo(pPai, pMae);
	// }

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

	// public List<Lider> listarLideres(){
	// return pessoaService.listarLideres();
	// }

	// public List<Coordenador> listarCoordenadores(){
	// return pessoaService.listarCoordenadores();
	// }

	// public List<Ministro> listarPresidentes(){
	// return pessoaService.listarPresidentes();
	// }

	public void excluirMembro(Pessoa pPessoa){
		membroService.excluir(pPessoa);

		JSFUtil.addInfoMessage("Membro “"+ membro.getNome()
								+ " "
								+ membro.getSobrenome()
								+ "” excuído com sucesso.");
	}

	public Pessoa getMembro(){
		return membro;
	}

	public void setMembro(Pessoa pMembro){
		membro = pMembro;
	}
}
