function assertNotNull(name, object){
  if (object == null){
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

function getUpdateTaskAjaxUrl(){
  var updateTaskAjaxUrlElement = $("#updateTaskAjaxUrl");
  assertNotNull("updateTaskAjaxUrlElement", updateTaskAjaxUrlElement);
  return updateTaskAjaxUrlElement.val();
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

$(document)
    .ready(
        function() {
          $(".unvalidateButton")
              .click(
                  function(event) {
                    var confirmed = confirm("Are you sure do you want to unvalidate this payment?");
                    if (!confirmed) {
                      event.preventDefault();
                    }
                  });
          $(".removeButton")
              .click(
                  function(event) {
                    var confirmed = confirm("WARNING: THIS ACTION CAN NOT BE UNDONE!\n\nAre you sure do you want to remove permanently this registration?");
                    if (!confirmed) {
                      event.preventDefault();
                    }
                  });

          // update history (TODO remove, initial history should be loaded in
          // page using a call to history controller in jsp via sitemesh)
          updateHistory();

        });