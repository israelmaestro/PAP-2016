package br.com.shepherd.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import br.com.shepherd.entity.Email;
import br.com.shepherd.entity.Endereco;
import br.com.shepherd.entity.Pessoa;
import br.com.shepherd.entity.PessoaAtendimento;
import br.com.shepherd.entity.PessoaCelula;
import br.com.shepherd.entity.PessoaSede;
import br.com.shepherd.entity.Telefone;
import br.com.shepherd.service.VisitanteService;
import br.com.shepherd.service.util.JSFUtil;

// @ManagedBean(name = "membroBean")
// @ViewScoped
@Named
@SessionScoped
public class VisitanteBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	@ManagedProperty("#{pessoaService}")
	private VisitanteService	visitanteService;

	private Pessoa				visitante;

	private List<PessoaCelula>	visitantesCelulasFiltro;
	private List<PessoaCelula>	visitantesCelulas;
	private List<PessoaSede>	visitantesSedes;
	// private List<PessoaSede> visitantesSedesFiltro;

	private PessoaCelula		visitanteCelula;
	private PessoaSede			visitanteSede;
	private PessoaAtendimento	visitanteAtendimento;
	private boolean				visitandoCelula		= false;

	/*
	 * Construtor e afins
	 */
	public VisitanteBean() throws IOException{
		visitante = new Pessoa();
		visitanteCelula = new PessoaCelula();
		visitanteSede = new PessoaSede();
		visitanteSede.setDataVisita(new Date());
		visitanteAtendimento = new PessoaAtendimento();
	}

	/**
	 * Adiciona um relacionamento de membro com a célula
	 *
	 * @param pAjax
	 * @throws IOException
	 */
	public void addCelula(AjaxBehaviorEvent pAjax) throws IOException{
		visitante.addRelacionamentoCelula(new PessoaCelula(), JSFUtil.getProperty("funcaoMembro"));
	}

	/**
	 * Adiciona um telefone à lista telefônica do membro
	 *
	 * @param pAjax
	 */
	public void botaoAdicionarTelefone(AjaxBehaviorEvent pAjax){
		visitante.addTelefone(new Telefone());
	}

	/**
	 * Remove um telefone da lista telefônica do membro
	 *
	 * @param pTelefone
	 */
	public void botaoRemoverTelefone(Telefone pTelefone){
		visitante.removeTelefone(pTelefone);
	}

	/**
	 * Adiciona um email à lista de emails do membro
	 *
	 * @param pAjax
	 */
	public void botaoAdicionarEmail(AjaxBehaviorEvent pAjax){
		visitante.addEmail(new Email());
	}

	/**
	 * Remove um email da lista de emails do membro
	 *
	 * @param pEmail
	 */
	public void botaoRemoverEmail(Email pEmail){
		visitante.removeEmail(pEmail);
	}

	/**
	 * Lista os eventos padrões registrados
	 *
	 * @return String[]
	 * @throws IOException
	 */
	public List<String> eventosVisitados() throws IOException{
		List<String> eventos = new ArrayList<String>();

		for(int i=0; i <= Integer.parseInt(JSFUtil.getProperty("countEventosPadrao")); i++){
			eventos.add(JSFUtil.getProperty("eventoPadrao_" + i));
		}

		return eventos;
	}

	/**
	 * Lista as possíveis situações do visitante
	 *
	 * @return List<String>
	 * @throws IOException
	 */
	public List<String> situacoes() throws IOException{
		List<String> situacoes = new ArrayList<String>();

		for(int i = 0; i <= Integer.parseInt(JSFUtil.getProperty("countSituacoes")); i++){
			situacoes.add(JSFUtil.getProperty("situacao_" + i));
		}

		return situacoes;
	}

	/**
	 * Retorna o endereço selecionado para o atendimento
	 *
	 * @param arg
	 * @return Endereco
	 */
	public Endereco getEnderecoAtendimento(String arg){
		if(arg.equals("visitante")){
			return visitante.getEndereco();
		} else if(arg.equals("sede")){
			return visitanteSede.getSede().getEndereco();
		} else{
			return new Endereco();
		}
	}

	/**
	 * Realiza o cadastro do membro, chamando cadastrar() de MembroService
	 *
	 * @return Lista (sucesso) / Cadastro (falha)
	 */
	public String cadastrar(){
		try{
			if(visitandoCelula){
				visitanteSede.setComAtendimento(false);
				visitante.addRelacionamentoCelula(	visitanteCelula,
													JSFUtil.getProperty("funcaoVisitante"));
			} else{
				visitante.addRelacionamentoSede(visitanteSede,
												JSFUtil.getProperty("funcaoVisitante"));

				if(visitanteSede.isComAtendimento()){
					visitanteAtendimento.getAtendimento().getEndereco().setId(null);
					visitanteAtendimento.getAtendimento()
										.setStatus(JSFUtil.getProperty("atendimentoStatus_1"));
					visitante.addRelacionamentoAtendimento(	visitanteAtendimento,
															JSFUtil.getProperty("funcaoAtendido"));
				}
			}

			visitanteService.cadastrar(visitante);

			JSFUtil.addInfoMessage("Visitante “"+ visitante.getNome()
									+ " "
									+ visitante.getSobrenome()
									+ "” cadastrado com sucesso!");

			visitante = new Pessoa();
			visitanteCelula = new PessoaCelula();
			visitanteSede = new PessoaSede();
			visitanteSede.setDataVisita(new Date());
			visitanteAtendimento = new PessoaAtendimento();

			return "visitanteListar";
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
			return "VisitanteCadastrar";
		}
	}

	/**
	 * Altera o membro desejado, chamando alterar() de MembroService
	 *
	 * @param pPessoa
	 */
	public void alterar(Pessoa pPessoa){
		visitanteService.alterar(pPessoa);

		JSFUtil.addInfoMessage("Visitante “"+ visitante.getNome()
								+ " "
								+ visitante.getSobrenome()
								+ "” alterado com sucesso.");
	}

	/**
	 * Retorna o status do atendimento
	 *
	 * @param pPessoa
	 * @return
	 */
	public String getStatusAtendimento(Pessoa pPessoa){
		return visitanteService.getStatusAtendimento(pPessoa);
	}

	/**
	 * Lista apenas as pessoas classificadas como visitantes das células
	 */
	public List<PessoaCelula> listarVisitantesCelulas(){
		return visitanteService.listarVisitantesCelulas();
	}

	/**
	 * Lista apenas as pessoas classificadas como visitantes das sedes
	 */
	public List<PessoaSede> listarVisitantesSedes(){
		return visitanteService.listarVisitantesSedes();
	}

	/**
	 * Lista todas as pessoas
	 */
	// public List<Pessoa> listarTodos(){
	// return visitanteService.listarTodos();
	// }

	// public List<Pessoa> listarExcluindo(Pessoa pPai, Pessoa pMae){
	// return pessoaService.listarExcluindo(pPai, pMae);
	// }

	/**
	 * Lista todos os homens
	 */
	public List<Pessoa> listarHomens(){
		return visitanteService.listarHomens();
	}

	/**
	 * Lista somente homens solteiros
	 */
	public List<Pessoa> listarHomensSolteiros(){
		return visitanteService.listarHomensSolteiros();
	}

	/**
	 * Lista todas as mulheres
	 */
	public List<Pessoa> listarMulheres(){
		return visitanteService.listarMulheres();
	}

	/**
	 * Lista somente as mulheres solteiras
	 */
	public List<Pessoa> listarMulheresSolteiras(){
		return visitanteService.listarMulheresSolteiras();
	}

	/**
	 * Exclui a pessoa selecionada
	 */
	public void excluir(Pessoa pPessoa){
		visitanteService.excluir(pPessoa);

		JSFUtil.addInfoMessage("Visitante excuído com sucesso.");
	}

	/*
	 * Getters e Setters
	 */
	public Pessoa getVisitante(){
		return visitante;
	}

	public void setVisitante(Pessoa pVisitante){
		visitante = pVisitante;
	}

	public List<PessoaCelula> getVisitantesCelulasFiltro(){
		return visitantesCelulasFiltro;
	}

	public void setVisitantesCelulasFiltro(List<PessoaCelula> pVisitantesCelulasFiltro){
		visitantesCelulasFiltro = pVisitantesCelulasFiltro;
	}

	public List<PessoaCelula> getVisitantesCelulas(){
		visitantesCelulas = visitanteService.listarVisitantesCelulas();
		return visitantesCelulas;
	}

	public void setVisitantesCelulas(List<PessoaCelula> pVisitantesCelulas){
		visitantesCelulas = pVisitantesCelulas;
	}

	public List<PessoaSede> getVisitantesSedes(){
		visitantesSedes = visitanteService.listarVisitantesSedes();
		return visitantesSedes;
	}

	public void setVisitantesSedes(List<PessoaSede> pVisitantesSedes){
		visitantesSedes = pVisitantesSedes;
	}

	public PessoaCelula getVisitanteCelula(){
		return visitanteCelula;
	}

	public void setVisitanteCelula(PessoaCelula pVisitanteCelula){
		visitanteCelula = pVisitanteCelula;
	}

	public PessoaSede getVisitanteSede(){
		return visitanteSede;
	}

	public void setVisitanteSede(PessoaSede pVisitanteSede){
		visitanteSede = pVisitanteSede;
	}

	public PessoaAtendimento getVisitanteAtendimento(){
		return visitanteAtendimento;
	}

	public void setVisitanteAtendimento(PessoaAtendimento pVisitanteAtendimento){
		visitanteAtendimento = pVisitanteAtendimento;
	}

	public boolean isVisitandoCelula(){
		return visitandoCelula;
	}

	public void setVisitandoCelula(boolean pVisitandoCelula){
		visitandoCelula = pVisitandoCelula;
	}

	public String getProperty(String pKey){
		try{
			return JSFUtil.getProperty(pKey);
		} catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}

}
