/**
  * Use to remove a guest in form.
  * guestContainer : JQuery component.
  *
**/
function removeGuest(guestContainer)  {
	guestContainer.find('.guest_name_input').val('');
	guestContainer.slideUp();
}

/**
 * Use to add a guest in form.
 *
**/
function addGuest()  {
	var nbVisibleGuests = $('.supp:visible').size();
	if (nbVisibleGuests == 2){
		alert('You can register only for 3 persons.');
	}else{
		$('.supp:hidden').first().slideDown();
	}
}

function hideEmptyGuests(){
	$('.supp:visible').each(function(index) {
		if( $(this).find('.guest_name_input').val().length === 0 ) {
			$(this).hide();
		}
	});
}

$(document).ready(function() {
	hideEmptyGuests();
	
	$(".remove_guest_button").on('click', function(event) {
		removeGuest($(this).parents(".supp"));
	});
	$("#add_guest_button").on('click', function(event) {
		addGuest($(this).parents(".supp"));
	});
});
