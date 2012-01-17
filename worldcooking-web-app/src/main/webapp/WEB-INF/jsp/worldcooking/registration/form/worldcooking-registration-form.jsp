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
	
	<title>${event.name} - Registration</title>
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
				<template-form:input 	id="additionalParticipant1NameInput" path="additionalParticipant1Name" 
										label="Guest 1 name" required="true" size="small-medium" cssClass="additionalParticipantNameInput" />
				<%-- guest 1 task --%>
				<template-form:select 	id="additionalParticipant1TaskInput" path="additionalParticipant1Task" 
										label="Guest 1 task" required="true" items="${availableTasks}" multiple="false" size="small-medium"/>
				<%-- guest 1 email --%>
				<template-form:input 	id="additionalParticipant1EmailAddressInput" path="additionalParticipant1EmailAddress" 
										label="Guest 1 e-mail address" size="small-medium" />
			</div>
			
			<div class="additionalParticipantArea">
				<!-- remove guest 2 button -->
				<template-form:button 	id="removeParticipant2Link" newGroup="true" cssClass="removeParticipantLink mishk_form-template_field-image"
										title="Remove guest 2">
					<img src="${removeParticipantImgUrl}" />
				</template-form:button>
				<%-- guest 2 name --%>
				<template-form:input 	id="additionalParticipant1NameInput" path="additionalParticipant2Name" 
										label="Guest 2 name" required="true" size="small-medium" cssClass="additionalParticipantNameInput" />
				<%-- guest 2 task --%>
				<template-form:select 	id="additionalParticipant1TaskInput" path="additionalParticipant2Task" 
										label="Guest 2 task" required="true" items="${availableTasks}" multiple="false" size="small-medium"/>
				<%-- guest 2 email --%>
				<template-form:input 	id="additionalParticipant1EmailAddressInput" path="additionalParticipant2EmailAddress" 
										label="Guest 2 e-mail address" size="small-medium" />
			</div>
			
			<form:errors cssClass="general_error error" />
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
			<template-form:button cssId="paypalSubmitButton" title="Pay online with Paypal or CB" >
<%-- 				<img src="<c:url value="/img/3rdparty/iconspedia/iconshock/Credit_cards_and_payment_icon/Paypal-256.png"/>" />
 --%>				Paypal or CB
			</template-form:button>
			<template-form:button cssId="manualSubmitButton" title="Pay directly to Matthieu Gaudet" >
				Manually to Matthieu Gaudet
			</template-form:button>
		</fieldset>
		</template:chapter>
</template-form:form>

</body>
</html>