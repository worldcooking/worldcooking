<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery"  %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui"  %>
<html>
<head>
	<jquery:require-static-resources />
	<jquery-ui:require-static-resources />
	<resources:require-css resourceURI="worldcooking/worldcooking-main.css"/>	
	<resources:require-script resourceURI="worldcooking/registration/form/worldcooking-registration-form.js" />
	
	<title>Worldcooking Peru - Registration</title>
</head>
<body>
	<form:form commandName="registration"   > 
	<form:hidden path="eventId"/>
	
	<template:chapter>
		<jsp:attribute name="title">Join us!</jsp:attribute>
		<jsp:body>
			<p>
				The price for this meal is <b>15 &euros; per person.</b><br /> This
				amount can pay the rent of the room, the ingredients for the meal
				and drinks.<br /> To pay you can use paypal. In this way the
				registration is immediate.<br /> You can also pay directly to
				Matthieu, Nidia, Ben, Nicolas, Fred or Toub, but your registration will be
				validated only when you give them the money.<br />
			</p>
				<div id="join0" class="form">
					<div class="form_element form_input">
						<label for="name0_field">* Your name : 
						<form:input path="subscriberParticipantName"  />
						<form:errors path="subscriberParticipantName"  cssClass="error" />
						</label>
					</div>
					<div class="form_element form_input">
						<label for="task0_select">* Your task :</label> 
						
					 	<form:select id="task0_select" path="subscriberParticipantTask" items="${availableTasks}" multiple="false" />   
					</div>
					<div class="form_element">
						<input id="add_guest_button" type="button" value="Add a guest" 
							title="Also register for an other person (that can be a friend or not)"
							style="margin-top:22px"/>
					</div>
				</div>

				<div id="join1" class="form supp">
					<div class="form_element form_input">
						<label for="name1_field">* Guest #1 name :</label> 
						<form:input class="guest_name_input" id="name1_field" name="name1" path="additionalParticipantsNames[0]"  />
					</div>
					<div class="form_element form_input">
						<label for="task1_select">* Guest #1 task :</label> 
						<form:select id="task1_select" path="additionalParticipantsTasks[0]" items="${availableTasks}" multiple="false" />   
					</div>
					<div class="form_element">
						<input class="remove_guest_button" type="button" value="Remove guest" style="margin-top:22px"/>
					</div>
				</div>
				<div id="join2" class="form supp">
					<div class="form_element form_input">
						<label for="name2_field">* Guest #2 name : 
						<form:input class="guest_name_input" id="name2_field" name="name2" path="additionalParticipantsNames[1]"  />
						</label>
					</div>
					<div class="form_element form_input">
						<label for="task2_select">* Guest #2 task :</label> 						
						<form:select id="task2_select" path="additionalParticipantsTasks[1]" items="${availableTasks}" multiple="false" />   

					</div>
					<div class="form_element">
						<input type="button" class="remove_guest_button" value="Remove guest" style="margin-top:22px"/>
					</div>
				</div>
				
				<div id="pay" class="form">
					<div class="form_element">
						<label for="email">* E-mail :</label> 
						<form:input id="email" path="emailAddress" />
						<form:errors path="emailAddress" cssClass="error" lang="en"  />
					</div>
					<div class="form_element form_input">
						<label for="payment_mode">* Payment :</label> 
						<form:select id="payment_mode" path="paymentMode" items="${availablePaymentModes}" multiple="false" />   
					</div><br/><br/>
					<div class="form_element">
						<input type="submit" value="Join" />
					</div>
				</div>
		</jsp:body>
	</template:chapter>
	</form:form>
</body>
</html>