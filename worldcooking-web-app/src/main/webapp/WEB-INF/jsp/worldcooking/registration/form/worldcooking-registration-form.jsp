<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery"  %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui"  %>
<%@ taglib prefix="template-form" uri="http://www.oups-asso.org/mish-k/tags/template-form"  %>
<html>
<head>
	<jquery:require-static-resources />
	<jquery-ui:require-static-resources />
	<resources:require-script resourceURI="worldcooking/registration/form/worldcooking-registration-form.js" />
	
	<title>Worldcooking Peru - Registration</title>
</head>
<body>
	
<template-form:form id="registrationForm" commandName="registration" >

	<%-- event id --%>
	<form:hidden id="eventIdHiddenInput" path="eventId" />
	
	<template:chapter title="Join us!">
		<p>
			The price for this meal is <b>15€ per person.</b>
			<br /> This amount can pay the rent of the room, the ingredients for the meal
			and drinks.
		</p>
		<fieldset>
			<legend>Participants</legend>
		
			<!-- add participant button -->

			<c:url var="addParticipantImgUrl" value="/img/3rdparty/iconspedia/FatCow/FatCow_Hosting/Add.png"/>
			<template-form:button 	id="addParticipantLink" newGroup="true" cssClass="mishk_form-template_field-image"
									title="Also register for an other participant (friend, girlfriend...)">
				<img src="${addParticipantImgUrl}" />
			</template-form:button>
									
			<%-- register name --%>
			<template-form:input 	id="subscriberParticipantNameInput" path="subscriberParticipantName" 
									label="Your name" required="true" size="small-medium" />
			<%-- register task --%>
			<template-form:select 	id="subscriberParticipantTaskInput" path="subscriberParticipantTask" 
									label="Your task" required="true" items="${availableTasks}" multiple="false" size="small-medium"/>
			<%-- register email --%>
			<template-form:input 	id="emailAddressInput" path="emailAddress" 
									label="Your e-mail address" required="true" size="small-medium" />
			
			
			<div class="additionalParticipantArea">
				
				<!-- remove guest 1 button -->
				<c:url var="removeParticipantImgUrl" value="/img/3rdparty/iconspedia/FatCow/FatCow_Hosting/Cancel.png"/>
				<template-form:button 	id="removeParticipant1Link" newGroup="true" cssClass="removeParticipantLink mishk_form-template_field-image"
										title="Remove guest 1">
					<img src="${removeParticipantImgUrl}" />
				</template-form:button>
				
				<%-- guest 1 name --%>
				<template-form:input 	id="additionalParticipant1NameInput" path="additionalParticipantsNames[0]" 
										label="Guest 1 name" size="small-medium" cssClass="additionalParticipantNameInput" />
				<%-- guest 1 task --%>
				<template-form:select 	id="additionalParticipant1TaskInput" path="additionalParticipantsTasks[0]" 
										label="Guest 1 task" items="${availableTasks}" multiple="false" size="small-medium"/>
				<%-- guest 1 email --%>
				<template-form:input 	id="additionalParticipant1EmailAddressInput" path="additionalParticipantsEmailAddresses[0]" 
										label="Guest 1 e-mail address" size="small-medium" />
			</div>
			
			<div class="additionalParticipantArea">
				<!-- remove guest 2 button -->
				<template-form:button 	id="removeParticipant2Link" newGroup="true" cssClass="removeParticipantLink mishk_form-template_field-image"
										title="Remove guest 2">
					<img src="${removeParticipantImgUrl}" />
				</template-form:button>
				<%-- guest 2 name --%>
				<template-form:input 	id="additionalParticipant1NameInput" path="additionalParticipantsNames[1]" 
										label="Guest 2 name" size="small-medium" cssClass="additionalParticipantNameInput" />
				<%-- guest 2 task --%>
				<template-form:select 	id="additionalParticipant1TaskInput" path="additionalParticipantsTasks[1]" 
										label="Guest 2 task" items="${availableTasks}" multiple="false" size="small-medium"/>
				<%-- guest 2 email --%>
				<template-form:input 	id="additionalParticipant1EmailAddressInput" path="additionalParticipantsEmailAddresses[1]" 
										label="Guest 2 e-mail address" size="small-medium" />
			</div>	
		</fieldset>
		
		<%-- payment mode --%>
		<form:hidden id="paymentModeHiddenInput" path="paymentMode" />
		
		<input id="paymentModePaypalHiddenInput" type="hidden" value="paypal" />
		<input id="paymentModeManualHiddenInput" type="hidden" value="manual-mgaudet" />
		
		<fieldset>
			<legend>Payment method</legend>
				To pay you can use paypal or CB. In this way the registration is immediate.
				<br /> You can also pay directly to Matthieu Gaudet
				, but your registration will be validated only when you give them the money (if a place is still available).
				<br /><br />
			<template-form:button cssId="paypalSubmitButton" priority="primary" title="Pay online with Paypal or CB" >
<%-- 				<img src="<c:url value="/img/3rdparty/iconspedia/iconshock/Credit_cards_and_payment_icon/Paypal-256.png"/>" />
 --%>				Paypal or CB
			</template-form:button>
			<template-form:button cssId="manualSubmitButton" priority="secondary" title="Pay directly to Matthieu Gaudet" >
				Manually to Matthieu Gaudet
			</template-form:button>
		</fieldset>
		</template:chapter>
</template-form:form>

	<%-- <form:form commandName="registration"   > 
	<form:hidden path="eventId"/>
	
		<template:chapter title="Join us!">
			<p>
				The price for this meal is <b>15 &euros; per person.</b><br /> This
				amount can pay the rent of the room, the ingredients for the meal
				and drinks.<br /> To pay you can use paypal. In this way the
				registration is immediate.<br /> You can also pay directly to
				Matthieu, Nidia, Ben, Nicolas, Fred or Toub, but your registration will be
				validated only when you give them the money.<br />
			</p>
				<fieldset>
				<legend>Participants</legend>
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
				</fieldset>
				<fieldset>
				<legend>Payment</legend>
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
				</fieldset>
		</template:chapter>
	</form:form> --%>
</body>
</html>