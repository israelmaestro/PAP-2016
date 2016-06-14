/*
 * Brazilian initialisation for the jQuery UI date picker plugin.
 */
/*
 jQuery(function($){
 $.datepicker.regional['pt-BR'] = {
 closeText : 'Fechar',
 prevText : '&#x3c;Anterior',
 nextText : 'Pr&oacute;ximo&#x3e;',
 currentText : 'Hoje',
 monthNames : [	'Janeiro',
 'Fevereiro',
 'Mar&ccedil;o',
 'Abril',
 'Maio',
 'Junho',
 'Julho',
 'Agosto',
 'Setembro',
 'Outubro',
 'Novembro',
 'Dezembro'],
 monthNamesShort : [	'Jan',
 'Fev',
 'Mar',
 'Abr',
 'Mai',
 'Jun',
 'Jul',
 'Ago',
 'Set',
 'Out',
 'Nov',
 'Dez'],
 dayNames : ['Domingo',
 'Segunda-feira',
 'Ter&ccedil;a-feira',
 'Quarta-feira',
 'Quinta-feira',
 'Sexta-feira',
 'Sabado'],
 dayNamesShort : ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
 dayNamesMin : ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
 weekHeader : 'Sm',
 dateFormat : 'dd/mm/yyyy',
 firstDay : 0,
 isRTL : false,
 showMonthAfterYear : false,
 yearSuffix : ''
 };
 $.datepicker.setDefaults($.datepicker.regional['pt-BR']);
 });
 */

function limparEndereco(){
	document.getElementById("input_form:logradouro").value = null;
	document.getElementById("input_form:numero").value = null;
	document.getElementById("input_form:bairro").value = null;
	document.getElementById("input_form:numero").value = null;
	document.getElementById("input_form:cidade").value = null;
	document.getElementById("input_form:uf").value = null;
	document.getElementById("input_form:cep").value = null;
	document.getElementById("input_form:pais").value = null;
	document.getElementById("input_form:coordenadas").value = null;
}

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

	PF("mapa").geocode(endereco);
}

function setMapaCode(){
	endereco = document.getElementById("input_form:gpsAddress").value;
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
