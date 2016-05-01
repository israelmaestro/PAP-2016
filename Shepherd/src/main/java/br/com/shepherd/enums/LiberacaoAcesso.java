package br.com.shepherd.enums;

public enum LiberacaoAcesso{
	CADASTRO_USUARIO("Cadastrar usuário");

	private String descricao;

	private LiberacaoAcesso(String pDescricao){
		descricao = pDescricao;
	}

	public String getDescricao(){
		return descricao;
	}

	public void setDescricao(String pDescricao){
		descricao = pDescricao;
	}
}
