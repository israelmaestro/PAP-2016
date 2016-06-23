function setFalse(x){
	document.getElementById(x).value = false;
}

/*
 * Brazilian initialization for the jQuery UI date picker plugin.
 */
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

// TODO: ### URGENTE!!! ARRUMAR AS PÁGINAS QUE CONTÉM ESSE MÉTODO
function limparEndereco(x){
	document.getElementById("input_form:logradouro" + x).value = null;
	document.getElementById("input_form:numero" + x).value = null;
	document.getElementById("input_form:bairro" + x).value = null;
	document.getElementById("input_form:numero" + x).value = null;
	document.getElementById("input_form:cidade" + x).value = null;
	document.getElementById("input_form:uf" + x).value = null;
	document.getElementById("input_form:cep" + x).value = null;
	document.getElementById("input_form:pais" + x).value = null;
	document.getElementById("input_form:coordenadas" + x).value = null;
}

// TODO: ### URGENTE!!! ARRUMAR AS PÁGINAS QUE CONTÉM ESSE MÉTODO
function localizaCoordenadas(x){
	PF("mapa" + x).geocode(null);

	var endereco = document.getElementById("input_form:logradouro" + x).value + " "
					+ document.getElementById("input_form:numero" + x).value
					+ " "
					+ document.getElementById("input_form:bairro" + x).value
					+ " "
					+ document.getElementById("input_form:cidade" + x).value
					+ " "
					+ document.getElementById("input_form:uf" + x).value
					+ " "
					+ document.getElementById("input_form:cep" + x).value
					+ " "
					+ document.getElementById("input_form:pais" + x).value;

	PF("mapa" + x).geocode(endereco);
}

// TODO: ### URGENTE!!! ARRUMAR AS PÁGINAS QUE CONTÉM ESSE MÉTODO
function setMapaCode(x){
	endereco = document.getElementById("input_form:gps" + x + "Address").value;
	PF("mapa" + x).geocode(endereco);
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
