<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Worldcooking Peru - Registration</title>
</head>
<body>
	<form:form commandName="registration"   > 
	<form:hidden path="eventId"/>
	
		<div class="main_chapter">
			<h2>Join us!</h2>
			<p>
				The price for this meal is <b>15 € per person.</b><br /> This
				amount can pay the rent of the room, the ingredients for the meal
				and drinks.<br /> To pay you can use paypal. In this way the
				registration is immediate.<br /> You can also pay directly to
				Matthieu, Nidia, Ben, Nicolas, Fred or Toub, but your registration will be
				validated only when you give them the money.<br />
			</p>
				<div id="join0" class="form">
					<div class="form_element form_input">
						<label for="name0_field">* Your name : 
						<form:input path="participantsNames[0]"  />
						<form:errors path="participantsNames"  cssClass="error" />
						</label>
					</div>
					<div class="form_element form_input">
						<label for="task0_select">* Your task :</label> 
						
					 	<form:select id="task0_select" path="participantTasks[0]" items="${availableTasks}" multiple="false" />   
					</div>
					<div class="form_element">
						<input type="button" value="Add a guest" title="Also register for an other person (that can be a friend or not, but please understand that we have a lot of problems with git and eclipse, so please!!!! it does not care who is it, just register it f**k!)"
							onclick="javascript:showOneMoreElement(['join1','join2'], 'You can register only for 3 persons.');" style="margin-top:22px"/>
					</div>
				</div>
				<div id="join1" class="form supp">
					<div class="form_element form_input">
						<label for="name1_field">* Guest #1 name :</label> 
						<form:input id="name1_field" name="name1" path="participantsNames[1]"  />
					</div>
					<div class="form_element form_input">
						<label for="task1_select">* Guest #1 task :</label> 
						<form:select id="task1_select" path="participantTasks[1]" items="${availableTasks}" multiple="false" />   
					</div>
					<div class="form_element">
						<input type="button" value="Remove guest"
							onclick="javascript:hideElement('join1'); document.getElementById('name1_field').value='';" style="margin-top:22px"/>
					</div>
				</div>
				<div id="join2" class="form supp">
					<div class="form_element form_input">
						<label for="name2_field">* Guest #2 name : 
						<form:input id="name2_field" name="name2" path="participantsNames[2]"  />
						</label>
					</div>
					<div class="form_element form_input">
						<label for="task2_select">* Guest #2 task :</label> 						
						<form:select id="task2_select" path="participantTasks[2]" items="${availableTasks}" multiple="false" />   

					</div>
					<div class="form_element">
						<input type="button" value="Remove guest"
							onclick="javascript:hideElement('join2'); document.getElementById('name2_field').value='';" style="margin-top:22px"/>
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
		</div> 
	</form:form>
</body>
</html>