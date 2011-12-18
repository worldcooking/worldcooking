$(document).ready(function() {
	$(".openIdForm").on("click", ".openIdLink", function(event) {
		// submit the form when user clicks the link
		$(this).parents(".openIdForm").submit();
	});
});
