function validateRegistration(validateButton){
	var formElement = validateButton.parents("form");
    var formAction = formElement.attr("action");
    var registrationIdElement = formElement.children(".registrationId");

    $.ajax({
    	  url: formAction,
    	  dataType: 'json',
    	  data: {registrationId : registrationIdElement.val()},
    	  complete: function(data, status) {
		      // TODO manage errors
		      updateHistory();
		      
		      updateValidatedRegistrations();
		      
		      updateUnvalidatedRegistrations();
	      }
    	});
}

function unvalidateRegistration(unvalidateButton){
	var formElement = unvalidateButton.parents("form");
    var formAction = formElement.attr("action");
    var registrationIdElement = formElement.children(".registrationId");

    $.ajax({
  	  url: formAction,
  	  dataType: 'json',
  	  data: {registrationId : registrationIdElement.val()},
  	  complete: function(data, status) {
		      // TODO manage errors
		      updateHistory();
		      
		      updateValidatedRegistrations();
		      
		      updateUnvalidatedRegistrations();
	      }
  	});
}