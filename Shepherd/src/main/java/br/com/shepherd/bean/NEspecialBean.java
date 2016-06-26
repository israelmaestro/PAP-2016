package br.com.shepherd.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.shepherd.entity.NEspecial;
import br.com.shepherd.service.NEspecialService;
import br.com.shepherd.service.util.JSFUtil;

@Named
@SessionScoped
public class NEspecialBean implements Serializable{
	private static final long	serialVersionUID	= 4229014895384999619L;

	@EJB
	private NEspecialService	nEspecialService;

	private NEspecial			nEspecial;

	private NEspecial			nEspecialTemp;

	public NEspecialBean(){
		nEspecial = new NEspecial();
		nEspecialTemp = new NEspecial();
	}

	public String cadastrar(){
		try{
			nEspecialService.cadastrar(nEspecial);

			JSFUtil.addInfoMessage("Necessiade Especial “"+ nEspecial.getNome()
									+ "” cadastrada com sucesso!");

			nEspecial = new NEspecial();

			return "nEspecialListar";
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
			return "nEspecialCadastrar";
		}
	}

	public String prepararAlteracao(NEspecial pNEspecial){
		setNEspecialTemp(pNEspecial);

		return "nEspecialAlterar";
	}

	public String prepararVisualizacao(NEspecial pNEspecial){
		setNEspecialTemp(pNEspecial);

		return "nEspecialVisualizar";
	}
	
	public String alterar(NEspecial pNEspecial) throws Exception{
		try{
			nEspecialService.alterar(pNEspecial);

			JSFUtil.addInfoMessage("Necessidade Especial alterada com sucesso.");
			return "nEspecialListar";
		} catch(Exception e){
			JSFUtil.addWarnMessage(e.getMessage());
			return "nEspecialAlterar";
		}
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

	public NEspecial getNEspecialTemp() {
		return nEspecialTemp;
	}

	public void setNEspecialTemp(NEspecial pNEspecialTemp) {
		nEspecialTemp = pNEspecialTemp;
	}
}
