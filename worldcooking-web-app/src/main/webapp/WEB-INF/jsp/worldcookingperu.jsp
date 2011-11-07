<%@page import="org.worldcooking.server.entity.payment.*"%>
<%@ page language="java"%>
<%@page import="org.worldcooking.server.entity.event.Subscription"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="org.worldcooking.server.entity.event.Event"%>
<%@ page import="org.worldcooking.server.entity.people.Participant"%>
<%@ page import="org.worldcooking.server.entity.event.Task"%>
<%@ page import="org.worldcooking.server.entity.payment.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${event.name}</title>
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
			<div class="adress" onmouseover="showElement('googlemap')"
				onmouseout="hideElement('googlemap')">
				<b>La soupe au Caillou</b><br /> 15 Rue Charles Gounod<br /> 31200
				Toulouse<br /> <a
					href="http://maps.google.fr/maps?f=q&amp;source=embed&amp;hl=fr&amp;geocode=&amp;q=la+soupe+aux+caillou&amp;aq=&amp;gl=fr&amp;g=15+Rue+Charles+Gounod,+31200+Toulouse&amp;ie=UTF8&amp;hq=la+soupe+aux+caillou&amp;hnear=Toulouse,+Haute-Garonne,+Midi-Pyr%C3%A9n%C3%A9es&amp;t=m&amp;vpsrc=0&amp;z=14&amp;iwloc=A&amp;cid=12869456708502713567&amp;ll=43.613686,1.424100"
					target="_blank">See the map</a>
				<iframe class="map" id="googlemap"
					src="http://maps.google.fr/maps?q=la+soupe+au+caillou&amp;oe=utf-8&amp;client=firefox-a&amp;ie=UTF8&amp;hl=fr&amp;hq=la+soupe+au+caillou&amp;hnear=Toulouse,+Midi-Pyr%C3%A9n%C3%A9es&amp;ll=43.625,1.405&amp;spn=0,0&amp;t=m&amp;z=14&amp;vpsrc=6&amp;iwloc=A&amp;cid=12869456708502713567&amp;output=embed">
				</iframe>
			</div>

		</div>
		<div class="main_chapter">
			<h2>Participants confirmed</h2>
			<div id="div_participants" class="participants">
				<table class="participants">
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
				<table class="participants">
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