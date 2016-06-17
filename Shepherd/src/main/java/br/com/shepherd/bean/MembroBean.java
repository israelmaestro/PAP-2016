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

// @ManagedBean(name = "membroBean")
// @ViewScoped
@Named
@SessionScoped
public class MembroBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{pessoaService}")
	private MembroService		membroService;

	private Pessoa				membro;

	private List<PessoaCelula>	pessoasCelulasFiltro;

	private List<PessoaCelula>	pessoasCelulas;

	/*
	 * Construtor e afins
	 */
	public MembroBean() throws IOException{
		membro = new Pessoa();
		membro.addRelacionamentoCelula(new PessoaCelula(), JSFUtil.getProperty("funcaoMembro"));
	}

	/**
	 * Adiciona um relacionamento de membro com a célula
	 *
	 * @param pAjax
	 * @throws IOException
	 */
	public void addCelula(AjaxBehaviorEvent pAjax) throws IOException{
		membro.addRelacionamentoCelula(new PessoaCelula(), JSFUtil.getProperty("funcaoMembro"));
	}

	/**
	 * Adiciona um telefone à lista telefônica do membro
	 *
	 * @param pAjax
	 */
	public void botaoAdicionarTelefone(AjaxBehaviorEvent pAjax){
		membro.addTelefone(new Telefone());
	}

	/**
	 * Remove um telefone da lista telefônica do membro
	 *
	 * @param pTelefone
	 */
	public void botaoRemoverTelefone(Telefone pTelefone){
		membro.removeTelefone(pTelefone);
	}

	/**
	 * Adiciona um email à lista de emails do membro
	 *
	 * @param pAjax
	 */
	public void botaoAdicionarEmail(AjaxBehaviorEvent pAjax){
		membro.addEmail(new Email());
	}

	/**
	 * Remove um email da lista de emails do membro
	 *
	 * @param pEmail
	 */
	public void botaoRemoverEmail(Email pEmail){
		membro.removeEmail(pEmail);
	}

	/**
	 * Realiza o cadastro do membro, chamando cadastrar() de MembroService
	 *
	 * @return Lista (sucesso) / Cadastro (falha)
	 */
	public String cadastrar(){
		try{
			membroService.cadastrar(membro);

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

	/**
	 * Altera o membro desejado, chamando alterar() de MembroService
	 *
	 * @param pPessoa
	 */
	public void alterar(Pessoa pPessoa){
		membroService.alterar(pPessoa);

		JSFUtil.addInfoMessage("Membro “"+ membro.getNome()
								+ " "
								+ membro.getSobrenome()
								+ "” alterado com sucesso.");
	}

	/**
	 * Lista apenas as pessoas classificadas como membros
	 */
	public List<PessoaCelula> listar(){
		return membroService.listar();
	}

	/**
	 * Lista todas as pessoas
	 */
	public List<Pessoa> listarTodos(){
		return membroService.listarTodos();
	}

	// public List<Pessoa> listarExcluindo(Pessoa pPai, Pessoa pMae){
	// return pessoaService.listarExcluindo(pPai, pMae);
	// }

	/**
	 * Lista todos os homens
	 */
	public List<Pessoa> listarHomens(){
		return membroService.listarHomens();
	}

	/**
	 * Lista somente homens solteiros
	 */
	public List<Pessoa> listarHomensSolteiros(){
		return membroService.listarHomensSolteiros();
	}

	/**
	 * Lista todas as mulheres
	 */
	public List<Pessoa> listarMulheres(){
		return membroService.listarMulheres();
	}

	/**
	 * Lista somente as mulheres solteiras
	 */
	public List<Pessoa> listarMulheresSolteiras(){
		return membroService.listarMulheresSolteiras();
	}

	/**
	 * Exclui a pessoa selecionada
	 */
	public void excluir(Pessoa pPessoa){
		membroService.excluir(pPessoa);

		JSFUtil.addInfoMessage("Membro excuído com sucesso.");
	}

	/*
	 * Getters e Setters
	 */
	public Pessoa getMembro(){
		return membro;
	}

	public void setMembro(Pessoa pMembro){
		membro = pMembro;
	}

	public List<PessoaCelula> getPessoasCelulasFiltro(){
		return pessoasCelulasFiltro;
	}

	public void setPessoasCelulasFiltro(List<PessoaCelula> pPessoasCelulasFiltro){
		pessoasCelulasFiltro = pPessoasCelulasFiltro;
	}

	public List<PessoaCelula> getPessoasCelulas(){
		pessoasCelulas = membroService.listar();
		return pessoasCelulas;
	}

	public void setPessoasCelulas(List<PessoaCelula> pPessoasCelulas){
		pessoasCelulas = pPessoasCelulas;
	}
}
