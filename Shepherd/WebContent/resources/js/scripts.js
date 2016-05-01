function setTrainee(x){
	if(x){
		document.getElementById("input_form:isTrainee").value = false;
	}

	return !x;
}

function tornarNulo(){
	return null;
}

function teclaNumerica(x){
	if(x < 48 || x > 57){
		if(x != 32){
			return false;
		}
	}
}

function preencherEndereco(cep){
	document.getElementById("input_form:cep").value = cep;
}

function adicionarTelefone(){
	var code1 = document.getElementById("telefone").innerHTML + document
						.getElementById("telefones").innerHTML;

	var code2 = "<b:button value=''/>";

	alert(code1);
	alert(code2);
	document.getElementById("telefones").innerHTML = code1 + code2;
}
