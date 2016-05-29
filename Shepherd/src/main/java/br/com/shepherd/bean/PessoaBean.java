package br.com.shepherd.bean;

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
import br.com.shepherd.service.PessoaService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@SessionScoped
public class PessoaBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{pessoaService}")
	private PessoaService		pessoaService;

	private Pessoa				pessoa;

	private PessoaCelula		pessoaCelula;

	public PessoaBean(){
		pessoa = new Pessoa();
		setPessoaCelula(new PessoaCelula());
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

	public String cadastrarMembro(){
		try{
			getPessoaCelula().setPessoa(pessoa);
			getPessoaCelula().setParticipacao(JSFUtil.getProperty("funcaoMembro"));

			pessoa.getPessoasCelulas().add(getPessoaCelula());

			pessoaService.cadastrarMembro(pessoa);

			JSFUtil.addInfoMessage("Membro “"+ pessoa.getNome() + " " + pessoa.getSobrenome()
									+ "” cadastrado com sucesso!");

			pessoa = new Pessoa();

			return "membroListar";
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
			return "membroCadastrar";
		}
	}

	public void alterarMembro(Pessoa pPessoa){
		pessoaService.alterarMembro(pPessoa);

		JSFUtil.addInfoMessage("Membro “"+ pessoa.getNome() + " " + pessoa.getSobrenome()
								+ "” alterado com sucesso.");
	}

	public List<PessoaCelula> listarMembros(){
		return pessoaService.listarMembros();
	}

	public List<Pessoa> listar(){
		return pessoaService.listar();
	}

	// public List<Pessoa> listarExcluindo(Pessoa pPai, Pessoa pMae){
	// return pessoaService.listarExcluindo(pPai, pMae);
	// }

	public List<Pessoa> listarHomens(){
		return pessoaService.listarHomens();
	}

	public List<Pessoa> listarHomensSolteiros(){
		return pessoaService.listarHomensSolteiros();
	}

	public List<Pessoa> listarMulheres(){
		return pessoaService.listarMulheres();
	}

	public List<Pessoa> listarMulheresSolteiras(){
		return pessoaService.listarMulheresSolteiras();
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
		pessoaService.excluir(pPessoa);

		JSFUtil.addInfoMessage("Membro “"+ pessoa.getNome() + " " + pessoa.getSobrenome()
								+ "” excuído com sucesso.");
	}

	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setPessoa(Pessoa pPessoa){
		pessoa = pPessoa;
	}

	public PessoaCelula getPessoaCelula(){
		return pessoaCelula;
	}

	public void setPessoaCelula(PessoaCelula pPessoaCelula){
		pessoaCelula = pPessoaCelula;
	}
}
