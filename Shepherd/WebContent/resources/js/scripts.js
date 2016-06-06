function localizaCoordenadas(){
	PF("mapa").geocode(null);

	var endereco = document.getElementById("input_form:logradouro").value + " "
					+ document.getElementById("input_form:numero").value
					+ " "
					+ document.getElementById("input_form:bairro").value
					+ " "
					+ document.getElementById("input_form:cidade").value
					+ " "
					+ document.getElementById("input_form:uf").value
					+ " "
					+ document.getElementById("input_form:cep").value
					+ " "
					+ document.getElementById("input_form:pais").value;

	// var endereco = PF("logradouro").jq.val() + " "
	// + PF("numero").jq.val()
	// + " "
	// + PF("bairro").jq.val()
	// + " "
	// + PF("cidade").jq.val()
	// + " "
	// + PF("uf").jq.val()
	// + " "
	// + PF("cep").jq.val()
	// + " "
	// + PF("pais").jq.val();
	PF("mapa").geocode(endereco);
}

function requiredField(id){
	document.getElementById(id).style.backgroundColor = "#FFC";
}

function alertarAoDesligar(x, y){
	if(!x.checked){
		if(confirm(y)){
			x.checked = false;
		} else{
			x.checked = true;
		}
	}
}

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
