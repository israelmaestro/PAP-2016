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
