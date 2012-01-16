function assertNotNull(name, object){
  if (object == null || object.length == 0){
    alert("Technical error: object '" + name + "' should not be null.");
  }
  // TODO test if a jquery item is empty as well
}

// TODO Javascript unit tests?

// TODO delegate object queries to dedicated class (shared between JS scripts)
// the class instance maintain the object references for better performances
function getParticipantElement(participantChild){
	  var participantElement = participantChild.parents(".participant");
	  assertNotNull("participantElement", participantElement);
	  return participantElement;
	}  

	function getUpdateRoleAjaxUrl(){
	  var updateRoleAjaxUrlElement = $("#updateRoleAjaxUrl");
	  assertNotNull("updateRoleAjaxUrlElement", updateRoleAjaxUrlElement);
	  return updateRoleAjaxUrlElement.val();
	}

	function getParticipantId(participantChild){
	  var participantIdElement = getParticipantElement(participantChild).find(".participantId");
	  assertNotNull("participantIdElement", participantIdElement);
	  
	  var participantId = participantIdElement.val();
	  assertNotNull("participantId", participantId);
	  
	  return participantId;
	}

	function getRegistrationId(participantChild){
	  var registrationIdElement = getParticipantElement(participantChild).find(".registrationId");
	  assertNotNull("registrationIdElement", registrationIdElement);
	  
	  var registrationId = participantIdElement.val();
	  assertNotNull("registrationId", registrationId);
	  
	  return registrationId;
	}

	function getAmountElement(participantChild){
	  var participantsElement = getParticipantElement(participantChild);
	    
	  var amountElementHtmlId = participantsElement.find(".amountHtmlId").val();
	  assertNotNull("amountElementHtmlId", amountElementHtmlId);

	  var amountElement = $("#" + amountElementHtmlId);
	  assertNotNull("amountElement", amountElement);

	  return amountElement;
	}
	
function updateValidatedRegistrations() {
  var validatedRegistrationsContainerElement = $('#validated_registrations');
  // update history
  $.ajax({
    url : $("#showAdminValidatedRegistrationsAjaxUrl").val(),
    success : function(data) {
    	validatedRegistrationsContainerElement.html(data);
    }
  });
}

function updateUnvalidatedRegistrations() {
	var unvalidatedRegistrationsContainerElement = $('#unvalidated_registrations');
  // update history
  $.ajax({
    url : $("#showAdminUnvalidatedRegistrationsAjaxUrl").val(),
    success : function(data) {
    	unvalidatedRegistrationsContainerElement.html(data);
    }
  });
}

$(document).ready(function() {
 	$("#mishk-template-layout_body-center").on("click", ".validateButton", function(event) {
	 // prevent form submit
    event.preventDefault();

    validateRegistration($(this));
    
  });
 
	$("#mishk-template-layout_body-center").on("click", ".unvalidateButton", function(event) {
    // prevent form submit
    event.preventDefault();

    unvalidateRegistration($(this));
  });
 
 
	$("#mishk-template-layout_body-center").on("click", ".removeButton", function(event) {
    var confirmed = confirm("WARNING: THIS ACTION CAN NOT BE UNDONE!\n\nAre you sure do you want to remove permanently this registration?");
    if (!confirmed) {
      event.preventDefault();
    }
  });

 	$("#mishk-template-layout_body-center").on("change", ".tasksSelect", function(event) {
	    updateRoleAjax( $(this));
	  });
 
 	$("#mishk-template-layout_body-center").on("change", ".tasksSelect", function(event) {
	    updateRoleAjax( $(this));
	  });
 
});