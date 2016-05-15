/**
 * https://viacep.com.br/exemplo/javascript/
 * 
 * Framework gratuito para preenchimento automático de endereços a partir do CEP
 * informado.
 * 
 * Alterado por Israel Oliveira Santos
 */
function limpaFormularioCep(){
	// Limpa valores do formulario de cep.
	document.getElementById('input_form:cep').value = "";
	document.getElementById('input_form:logradouro').value = "";
	document.getElementById('input_form:logradouro2').value = "";
	document.getElementById('input_form:bairro').value = "";
	document.getElementById('input_form:cidade').value = "";
	document.getElementById('input_form:uf').value = "";
}

function preencherCampos(conteudo){
	if(conteudo.cep != undefined){
		// Atualiza os campos com os valores.
		document.getElementById('input_form:logradouro').value = conteudo.logradouro;
		document.getElementById('input_form:logradouro2').value = conteudo.logradouro;
		document.getElementById('input_form:bairro').value = conteudo.bairro;
		document.getElementById('input_form:cidade').value = conteudo.localidade;
		document.getElementById('input_form:uf').value = conteudo.uf;
		document.getElementById('input_form:pais').value = "Brasil";
	} // end if.
	else{
		// CEP não Encontrado.
		alert("CEP não encontrado.");
		limpaFormularioCep();
	}
}

function pesquisaCep(valor){

	// Nova variável "cep" somente com dígitos.
	var cep = valor.replace(/\D/g, '');

	// Verifica se campo cep possui valor informado.
	if(cep != ""){

		// Expressão regular para validar o CEP.
		var validaCep = /^[0-9]{8}$/;

		// Valida o formato do CEP.
		if(validaCep.test(cep)){

			// Preenche os campos com "..." enquanto consulta webservice.
			document.getElementById('input_form:logradouro').value = "...";
			document.getElementById('input_form:logradouro2').value = "...";
			document.getElementById('input_form:bairro').value = "...";
			document.getElementById('input_form:cidade').value = "...";
			document.getElementById('input_form:uf').value = "...";

			// Cria um elemento javascript.
			var script = document.createElement('script');

			// Sincroniza com o callback.
			script.src = 'http://viacep.com.br/ws/' + cep + '/json/?callback=preencherCampos';

			// Insere script no documento e carrega o conteúdo.
			document.body.appendChild(script);

		} // end if.
		else{
			// cep é inválido.
			limpaFormularioCep();
			alert("Formato de CEP inválido.");
		}
	} // end if.
	else{
		// cep sem valor, limpa formulario.
		limpaFormularioCep();
	}
}
