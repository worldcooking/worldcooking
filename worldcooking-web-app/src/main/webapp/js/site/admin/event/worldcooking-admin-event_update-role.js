
function updateRole(eventRoleIdElement, newEventRoleId){
  if (eventRoleIdElement.val() != newEventRoleId) {
    // set new value (in case of error)
	  eventRoleIdElement.val(newEventRoleId);
    // highlight field
	  eventRoleIdElement.effect("highlight", {}, 1000);
  }
}

/**
 * Update the registration amount with a JQuery effect.
 * @param amountElement
 * @param newAmount
 */
function updateAmount(amountElement, newAmount){
  if (amountElement.html() != newAmount) {
    // hide amount with slide up effect
    amountElement.slideUp('fast', function() {
      // update amount
      amountElement.html(newAmount);
      // show amount with slide up effect
      amountElement.slideDown('slow');
    });
  }
}

function updateRoleAjax(eventRoleIdElement){
	assertNotNull("eventRoleIdElement", eventRoleIdElement);

    var updateRoleAjaxUrl = getUpdateRoleAjaxUrl();

    var participantId = getParticipantId(eventRoleIdElement);
    
    var amountElement = getAmountElement(eventRoleIdElement);

     // Ajax call to task update service
    jQuery.getJSON(updateRoleAjaxUrl, {
      participantId : participantId,
      eventRoleId : eventRoleIdElement.val()
    }, function(data, status) {
    //TODO manage errors
      updateRole(eventRoleIdElement, data.eventRoleId);
      updateAmount(amountElement, data.newAmount);
      updateHistory();
    });
}
