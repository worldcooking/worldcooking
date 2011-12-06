<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="loc" uri="http://www.oups-asso.org/tags/localization"  %>
<%@page import="javax.servlet.jsp.JspException"%>

<html>
<head>
	<title>${event.name}</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/3rdparty/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/3rdparty/jquery-ui-1.8.16/ui/minified/jquery-ui.min.js"></script>	
	<!-- load JS and CSS resources from localization taglib (using Spring mvc:resources interceptor defined in spring-web-context.xml) -->
	<loc:import-static-resources />
</head>
<body>

<c:if test="${event.registrationClosed == true}" >
<div class="main_chapter">
		<h2>Registration closed!</h2>
		<p>
			The event is now full, so <b>the registration is closed</b>. 
			<br/>If you were thinking to come, but do not have payed yet, you are not in the confirmed list, so you will have to be faster next time.
			<br/>To be informed of the next event, feel free to join the world-cooking mailing-list by sending us a mail: <a href="mailto:matthieutrashbox@gmail.com">matthieutrashbox@gmail.com</a>.
		</p>
	</div>
</c:if>
<c:if test="${event.registrationClosed == false}" >
<div class="main_chapter">
		<h2>Join us!</h2>
		<p>
			The price for this meal is <b>15€ per person. This meal will not generate any profit,</b><br/> 
			This amount goes to pay for the location, all the drinks and the food.
			<br/> To pay you can :
			<ul>
				<li>use paypal or CB: in this way the registration is immediate.</li>
				<li>pay directly to Matthieu, Nidia, Ben, Nicolas, Fred or Toub if you
			have the opportunity to see one of them very soon. Your registration
			will be validated only when you give them the money.</li>
			</ul>
			<br/><br/>
			<center><input type="button" value="JOIN WORLDCOOKING PERU!" class="join" OnClick="window.location.href='registration'"/></center><br/> 
		</p>
	</div>
</c:if>
	<div class="main_chapter">
		<h2>Informations</h2>
		<p>${event.information}</p>
		<div class="information">
			<h3>Menu :</h3>
			- Causa Rellena<br /> - Lomo Saltado<br /> - Mazamorra Morada<br />
		</div>
		<div class="information">
			<h3>Date and Time :</h3>
			Friday November 25th 2011, 8pm (4pm if you're cooking)<br /> <a
				href="http://www.google.com/calendar/event?action=TEMPLATE&text=Worldcooking%20Peru&dates=20111125T190000Z/20111125T230000Z&details=Peruvian%20meal%20shared%20%40%20La%20soupe%20au%20Caillou.%0A%0AWarning%20%3A%20be%20sure%20you%20registered!!!%0AWarning%202%20%3A%20if%20you're%20cooking%20be%20there%20at%204p.m.&location=La%20soupe%20au%20Caillou&trp=false&sprop=&sprop=name:"
				target="_blank"> Add to google Agenda</a>
		</div>
		<div class="information">
			<h3>Place :</h3>
			<loc:map mapContainerId="locationMap" 
				name="La soupe au Caillou" 
				addressLine1="15 Rue Charles Gounod" 
				addressLine2="31200 Toulouse"
						latitude="43.61368640000001" longitude="1.4242076000000452" width="50%" height="30%"
						flag="http://localhost:8080/worldcooking-web-app/img/restaurant-30px.png" />
		</div>
		<div class="main_chapter">
			<h2>Participants confirmed</h2>
			<div id="div_participants" class="participants">
				<table class="table">
					<thead>
						<tr>
							<th>Participant
								(${event.nbParticipants}/${event.nbParticipantsMax})</th>
							<c:forEach var="task" items="${event.tasks}">
								<th>${task.name} (${task.totalRegister}/${task.totalMax})</th>
							</c:forEach>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="participant"
							items="${event.validatedParticipantsTaskOrdered}">
							<tr>
								<th>${participant.name}</th>
								<c:forEach var="task" items="${event.tasks}">
									<td><c:if test="${task.id == participant.taskId}">
											<input type="radio" name="task${participant.id}"
												value="${participant.taskId}" checked="checked" />
										</c:if> <c:if test="${task.id != participant.taskId}">
											<input type="radio" name="task${participant.id}"
												value="${task.id}" disabled="disabled" />
										</c:if></td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
<c:if test="${event.registrationClosed == false}" >
		<div class="main_chapter">
			<h2>Participants waiting for payment confirmation</h2>
			<div id="div_participants" class="participants">
				<table class="table">
					<thead>
						<tr>
							<th>Participant waiting for confirmation
								(${event.nbParticipantsWaiting})</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pwaiting" items="${event.waitingParticipants}">
							<tr>
								<th>${pwaiting}</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</c:if>
	</div>
</body>
</html>