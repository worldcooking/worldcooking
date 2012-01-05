/**
  * Use to remove a guest in form.
  * guestContainer : JQuery component.
  *
**/
function removeGuest(guestContainer)  {
	guestContainer.find('.additionalParticipantNameInput').val('');
	guestContainer.hide("fade", {}, 500);
}

/**
 * Use to add a guest in form.
 *
**/
function addGuest()  {
	var nbVisibleGuests = $('.additionalParticipantArea:visible').size();
	if (nbVisibleGuests == 2){
		alert('You can register only for 3 persons.');
	}else{
		$('.additionalParticipantArea:hidden').first().show("fade", {}, 500);
	}
}

function hideEmptyGuests(){
	$('.additionalParticipantArea:visible').each(function(index) {
		if( $(this).find('.additionalParticipantNameInput').val().length === 0 ) {
			$(this).hide();
		}
	});
}

$(document).ready(function() {
	hideEmptyGuests();
	
	$(".removeParticipantLink").on('click', function(event) {
		removeGuest($(this).parents(".additionalParticipantArea"));
	});
	$("#addParticipantLink").on('click', function(event) {
		addGuest($(this).parents(".additionalParticipantArea"));
	});
	$("#paypalSubmitButton").on('click', function(event) {
		$("#paymentModeHiddenInput").val($("#paymentModePaypalHiddenInput").val());
		$(this).parents("form").submit();
	});
	$("#manualSubmitButton").on('click', function(event) {
		$("#paymentModeHiddenInput").val($("#paymentModeManualHiddenInput").val());
		$(this).parents("form").submit();
	});
});
