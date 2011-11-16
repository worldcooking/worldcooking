$(document).ready(function() {
   // do stuff when DOM is ready
	$(".validateButton").click(function(event) {
	     var confirmed = confirm("Are you sure do you want to validate this payment?");
	     if (!confirmed){
	    	 event.preventDefault();
	     }
	   });
	$(".unvalidateButton").click(function(event) {
	     var confirmed = confirm("Are you sure do you want to unvalidate this payment?");
	     if (!confirmed){
	    	 event.preventDefault();
	     }
	   });
	$(".removeButton").click(function(event) {
	     var confirmed = confirm("WARNING: THIS ACTION CAN NOT BE UNDONE!\n\nAre you sure do you want to remove permanently this registration?");
	     if (!confirmed){
	    	 event.preventDefault();
	     }
	   });
	
	
	$(".changeTaskButton").hide();
	
	$(".tasksSelect").change(function(event) {
	     var confirmed = confirm("Are you sure do you want to update participant task?");
	     if (confirmed){
	    	 $(this).parent("form").submit();
	     }
	   });
	
 });