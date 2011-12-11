$(document).ready(function() {
  $(".validateButton").click(function(event) {
    // prevent form submit
    event.preventDefault();

    var formElement = $(this).parents("form");
    var formAction = formElement.attr("action");
    var registrationIdElement = formElement.children(".registrationId");

    alert(formAction);
    
    jQuery.getJSON(formAction, {
      registrationId : registrationIdElement.val()
    }, function(data, status) {
      // TODO manage errors
      updateHistory();
    });
  });

});