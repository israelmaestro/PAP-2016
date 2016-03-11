function setTrainee(x){
	if(x){
		document.getElementById("input_form:isTrainee").value = false;
	}

	return !x;
}

function teclaNumerica(x){
	if((x < 48 || x > 57) && x != 13){
		return false;
	}
}

function atualizacep(cep){
	cep = cep.replace(/\D/g, "")
	url = "http://cep.correiocontrol.com.br/" + cep + ".js"
	s = document.createElement("script")
	s.setAttribute("charset", "utf-8")
	s.src = url
	document.querySelector("body").appendChild(s)
}

function correiocontrolcep(valor){
	if(valor.erro){
		alert("CEP não encontrado.");
		return;
	}

	document.getElementById("input_form:logradouro").value = valor.logradouro;
	document.getElementById("input_form:logradouro2").value = valor.logradouro;
	document.getElementById("input_form:bairro").value = valor.bairro
	document.getElementById("input_form:cidade").value = valor.localidade
	document.getElementById("input_form:uf").value = valor.uf
}
