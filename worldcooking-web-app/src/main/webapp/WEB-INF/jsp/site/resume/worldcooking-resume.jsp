<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="loc" uri="http://www.mishk.org/tags/map"  %>
<%@ taglib prefix="resources" uri="http://www.mishk.org/tags/resources"  %>
<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"  %>
<%@ taglib prefix="jquery-ui" uri="http://www.mishk.org/tags/jquery-ui" %>
<%@ taglib prefix="jquery" uri="http://www.mishk.org/tags/jquery" %>
<%@ taglib prefix="template-form" uri="http://www.mishk.org/tags/template-form"  %>

<html>
<head>
	<c:if test="${not empty event}">
		<title>${event.name}</title>
	</c:if>
	<c:if test="${empty event}">
		<title>Worldcooking</title>
	</c:if>
	<jquery:require-static-resources />
	<jquery-ui:require-static-resources />
	<resources:require-css resourceURI="site/resume/worldcooking-resume.css"/>
</head>
<body>  

<c:if test="${empty event}">
	<template:chapter title="No event planned.">
			<p>The next event is still not planned. Come back again here in a few days...
			</p>
	</template:chapter>
</c:if>
<c:if test="${not empty event}">

	<c:if test="${event.registrationClosed == true}" >
		<template:chapter title="Registration closed!">
			<p>The event is now full, so <b>the registration is closed</b>. 
				<br/>If you were thinking to come, but do not have payed yet, you are not in the confirmed list, so you will have to be faster next time.
				<br/>To be informed of the next event, feel free to join the world-cooking mailing-list by sending us a mail: <a href="mailto:matthieutrashbox@gmail.com">matthieutrashbox@gmail.com</a>.
			</p>
		</template:chapter>
	</c:if>
	<c:if test="${event.registrationClosed == false}" >
		<template:chapter title="Join us!">
			<p>
			The price for this meal is <b>15&euro; per person. This meal will not generate any profit,</b>
			<br/>
			<br/>
			<center>
				<c:url var="joinEventUrl" value="/event/${event.reference}/registration"/>
				<form action="${joinEventUrl}">
					<template-form:submit title="JOIN us now!" value="JOIN ${event.name}!" cssClass="centered" />
				
				</form>
			</center>
			<br/> 
			</p>
		</template:chapter>
	</c:if>
	<template:chapter title="Informations">
			<p>Up to 36 persons will share a brazilian meal in the restaurant La soupe au Caillou.<br>
				Our chef will be Mariana.<br>
				<br>
				To participate in this event you must book and pay in advance.<br>
				The price for the meal is 15 € per person. This amount is used entirely to cover the cost of the evening.
				<br>
				<br>
				When registering you have to choose a task from the following ones:
				<ul>
					<li><b>cooking with Mariana</b> from 4pm</li>
					<li><b>prepare the room and settings the table</b> from 6:30pm</li>
					<li><b>cleaning the room</b> after the dinner</li>
					<li><b>doing the dishes</b> after the dinner</li>
				</ul>
			</p>
			<div class="information">
				<h3>Menu :</h3>
				<ul>
					<li><b>Salada de grao-de-bico com bacalhau</b></li>
					<li><b>Moquequa baiana</b></li>
					<li><b>Mousse de maracuja</b></li>
				</ul>
			</div>
			<div class="information">
				<h3>Date and Time :</h3>
				Friday September 28th 2012, 7pm (4pm if you're cooking)<br /> <a
					href="http://www.google.com/calendar/event?action=TEMPLATE&text=Worldcooking%20Brazil&dates=20120928T180000Z/20120929T000000Z&details=Portuguese%20meal%20shared%20%40%20La%20soupe%20au%20Caillou.%0A%0AWarning%20%3A%20be%20sure%20you%20registered!!!%0AWarning%202%20%3A%20if%20you're%20cooking%20be%20there%20at%204p.m.&location=La%20soupe%20au%20Caillou&trp=false&sprop=&sprop=name:"
					target="_blank"> Add to google Agenda</a>
			</div>
			<div class="information">
				<h3>Place :</h3>
				<c:url var="iconUrl" value="/img/restaurant-30px.png" />
				La soupe au Caillou<br/>
				15 Rue Charles Gounod<br/>
				31200 Toulouse</br>
				<%--<loc:map mapContainerId="locationMap" 
					placeName="La soupe au Caillou" 
					addressLine1="15 Rue Charles Gounod" 
					addressLine2="31200 Toulouse"
							latitude="43.61368640000001" longitude="1.4242076000000452" width="50%" height="30%"
							flag="${iconUrl}" />--%>
			</div>
	</template:chapter>
	<template:chapter title="Participants confirmed">
			<div id="div_participants" class="participants">
				<template:table>
					<jsp:attribute name="header">
						<th>Participant (${event.nbParticipants}/${event.nbParticipantsMax})</th>
						<c:forEach var="task" items="${event.tasks}">
							<th>${task.name} (${task.totalRegister}/${task.totalMax})</th>
						</c:forEach>
					</jsp:attribute>
					<jsp:attribute name="content">
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
					</jsp:attribute>
				</template:table>
			</div>
	</template:chapter>
	<c:if test="${event.registrationClosed == false}" >
		<template:chapter title="Participants waiting for payment confirmation">
				<template:table>
					<jsp:attribute name="header">
						<th>Participant waiting for confirmation (${event.nbParticipantsWaiting})</th>
					</jsp:attribute>
					<jsp:attribute name="content">
						<c:forEach var="pwaiting" items="${event.waitingParticipants}">
							<tr>
								<th>${pwaiting}</th>
							</tr>
						</c:forEach>
					</jsp:attribute>
				</template:table>
		</template:chapter>
	</c:if>
</c:if>
</body>
</html>