/**
  * Use to hide a component.
  * nom : name of the component.
  *
**/
function hideElement(nom)  {
	var element = document.getElementById(nom);
	element.style.display="none";
}

/**
  * Use to hide a component.
  * nom : name of the component.
  *
**/
function showElement(nom)  {
	var element = document.getElementById(nom);
	element.style.display="block";
}

/**
  * show the first component hidden.
  * params : array of elements names.
  *
**/
function showOneMoreElement(params, message)  {
	var found = false;
	for (var i = 0; i < params.length; i++) {
		var element = document.getElementById(params[i]);
		if (element != null && (element.style.display == "none" || element.style.display == "")) {
			element.style.display="block";
			i = params.length;
			found = true;
		}
	}
	if (!found) {
	
		alert(message);
	}
}
/**
  * Clear inputs values.
  * params : array of elements names.
  *
**/
function cleanInputs(params) {
	for (var i = 0; i < params.length; i++) {
		document.getElementById(params[i]).value='';
	}
}