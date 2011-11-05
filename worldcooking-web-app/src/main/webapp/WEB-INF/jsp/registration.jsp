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
				Matthieu, Nidia, Ben, Nicolas or Toub, but your registration will be
				validated only when you give them the money.<br />
			</p>
			<form>
				<div id="join0" class="join">
					<div class="join_element">
						<label for="name0_field">* Your name : 
						<form:input path="participantsNames[0]"  />
						<form:errors path="participantsNames[0]" cssClass="error" />
						</label>
					</div>
					<div class="join_element">
						<label for="task0_select">* Your task :</label> 
						
					 	<form:select id="task0_select" path="participantTasks[0]" items="${availableTasks}" multiple="false" />   
					</div>
					<input type="button" value="Add a guest" title="Also register for an other person (that can be a friend or not, but please understand that we have a lot of problems with git and eclipse, so please!!!! it does not care who is it, just register it f**k!)"
						onclick="javascript:showOneMoreElement(['join1','join2'], 'You can register only for 3 persons.');" />
				</div>
				<div id="join1" class="join supp">
					<div class="join_element">
						<label for="name1_field">* Guest #1 name :</label> 
						<form:input id="name1_field" name="name1" path="participantsNames[1]"  />
						
					</div>
					<div class="join_element">
						<label for="task1_select">* Guest #1 task :</label> 
						<form:select id="task1_select" path="participantTasks[1]" items="${availableTasks}" multiple="false" />   
						
					</div>
					<input type="button" value="Remove guest"
						onclick="javascript:hideElement('join1'); document.getElementById('name1_field').value='';" />
				</div>
				<div id="join2" class="join supp">
					<div class="join_element">
						<label for="name2_field">* Guest #2 name : 
						<form:input id="name2_field" name="name2" path="participantsNames[2]"  />
						</label>
					</div>
					<div class="join_element">
						<label for="task2_select">* Guest #2 task :</label> 						
						<form:select id="task2_select" path="participantTasks[2]" items="${availableTasks}" multiple="false" />   

					</div>
					<input type="button" value="Remove guest"
						onclick="javascript:hideElement('join2'); document.getElementById('name2_field').value='';" />
				</div>
				
				
				<div id="pay" class="join">
					<div class="join_element">
						<label for="email">* E-mail :</label> 
						<form:input id="email" path="emailAddress" />
						<form:errors path="emailAddress" cssClass="error" lang="en"  />
					</div>
					<!-- 
					<div class="join_element">
						<label for="payment_mode">* Payment :</label> <select
							id="payment_mode">
							<option value="paypal">Paypal</option>
							<option value="ntorres">Pay to Benjamin Levine</option>
							<option value="mgaudet">Pay to Matthieu Gaudet</option>
							<option value="ngruyer">Pay to Nicolas Gruyer</option>
							<option value="ntoublanc">Pay to Nicolas Toublanc</option>
							<option value="ntorres">Pay to Nidia Torres</option>
						</select>
					</div>
					 -->
					<br /> <br /> <input type="submit" value="Join" />
				</div>
				
			</form>
		</div> 
	</form:form>

</body>
</html>