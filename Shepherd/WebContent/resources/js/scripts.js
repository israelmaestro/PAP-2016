function geocode(){
	PF('geoMap').geocode(document.getElementById('address').value);
}

function reverseGeocode(){
	var lat = document.getElementById('lat').value, lng = document.getElementById('lng').value;

	PF('revGeoMap').reverseGeocode(lat, lng);
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
