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
	if((x < 48 || x > 57) // Liberar intervalo numérico
		&& (x < 37 || x > 40) // Liberar intervalo de setas
		&& (x < 112 || x > 123) // Liberar intervalo de teclas F1 ~ F12
		&& x != 8 // Liberar backspace
		&& x != 9 // Liberar TAB
		&& x != 13 // Liberar ENTER
		&& x != 16){ // Liberar ESC
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
