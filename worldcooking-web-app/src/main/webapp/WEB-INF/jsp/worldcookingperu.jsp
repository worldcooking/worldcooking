<%@page import="org.worldcooking.server.entity.payment.PaypalPaymentMode"%>
<%@page import="org.worldcooking.server.entity.payment.Payment"%>
<%@page import="org.worldcooking.server.entity.event.Subscription"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java"%>
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

<%
	//todo retrieve form persistence
	Event wcPeru = new Event();
	wcPeru.setMaxParticipants(36);

	wcPeru.addAvailableTask(new Task("Chief",
			"Choose the menu and cook", 1));
	wcPeru.addAvailableTask(new Task("Cooking",
			"Cooking the meal with the chief", 7));
	wcPeru.addAvailableTask(new Task("Setting the table",
			"Setting the table", 10));
	wcPeru.addAvailableTask(new Task("Doing the dishes",
			"Doing the dishes", 9));
	wcPeru.addAvailableTask(new Task("Cleaning the room",
			"Cleaning the room", 9));

	Payment payment = new Payment();
	payment.setAmount(15D);
	payment.setPerceptionTime(new Date());
	PaypalPaymentMode mode = new PaypalPaymentMode();
	mode.setUser("userToto");
	payment.setMode(mode);
	Subscription subscription0 = new Subscription("toto@tata.com",
			payment, wcPeru);
	wcPeru.addSubscription(subscription0);

	wcPeru.setName("Worldcooking Peru");
	wcPeru.setDescription("Up to 39 persons will share a peruvian meal in the restaurant La soupe au Caillou.<br /> Our chef will be Nidia Torres.<br />"
			+ "<br /> To participate in this event you must book and pay in advance.<br /> The price for the meal is 15 € per person. This amount is used entirely"
			+ " to cover the cost of the evening.<br /> <br /> This year we ask each person to help. When registering you have to choose a task from the"
			+ " following ones:<br /> - Cooking with Nidia from 4pm<br /> - Set the table<br /> - Doing the dishes<br /> - Cleaning the room<br />");

	Map<Participant, Task> participantsToTasksMap = new HashMap<Participant, Task>();
	Map<Task, List<Participant>> tasksToParticipantsMap = new HashMap<Task, List<Participant>>();
	List<Task> tasks = wcPeru.getAvailableTasks();
	Map<Task, Integer> tasksNumber = new HashMap<Task, Integer>();
	Set<Participant> participantsSet = participantsToTasksMap.keySet();
%>
<html>
<head>
<title><%=wcPeru.getName()%></title>
</head>
<body onload="javascript:cleanInputs(['name1_field','name2_field']);">
	<div class="main_chapter">
		<h2>Informations</h2>
		<p><%=wcPeru.getDescription()%></p>
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
			<h2>Participants</h2>
			<div id="div_participants" class="participants">
				<table class="participants">
					<thead>
						<tr>
							<th>Participant (<%=participantsToTasksMap.keySet().size()%>/<%=wcPeru.getMaxParticipants()%>)
							</th>
							<%
								for (Task t : tasks) {
									List<Participant> participants = tasksToParticipantsMap.get(t);
									int partNumber;
									if (participants == null) {
										partNumber = 0;
									} else {
										partNumber = participants.size();
									}
							%><th><%=t.getName()%> (<%=partNumber%>/<%=t.getNbMax()%>)</th>
							<%
								}
							%>
						</tr>
					</thead>
					<tbody>
						<%
							for (Participant p : participantsSet) {
								if (p.getSubscription() != null
										&& p.getSubscription().getPayment() != null
										&& p.getSubscription().getPayment().getMode()
												.isOnline()) {
						%>
								<tr>
									<th><%=p.getName()%></th>
									<%
										for (Task t : tasks) {
													if (t.getId().equals(t.getId())) {
									%>
												<td><input type="radio" name="task0" value="<%=t.getId()%>"
													checked="checked" /></td>
											<%
												} else {
											%>
												<td><input type="radio" name="task0" disabled="disabled"
														value="<%=t.getId()%>" /></td>
											<%
												}
														}
											%>
									
								</tr>
								<%
									}
									}
								%>
					</tbody>
				</table>
			</div>
		</div>
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
						<label for="name0_field">* Your name : <input
							id="name0_field" name="name0" type="text" />
						</label>
					</div>
					<div class="join_element">
						<label for="task0_select">* Your task :</label> 
						<select
							id="task0_select" name="task0">
							<%
								for (Task t : tasks) {
							%>
							<option value="<%=t.getId()%>"><%=t.getName()%></option>
							<%
								}
							%>
						</select>
					</div>
					<input type="button" value="Add a guest"
						onclick="javascript:showOneMoreElement(['join1','join2'], 'You can register only for 3 persons.');" />
				</div>
				<div id="join1" class="join supp">
					<div class="join_element">
						<label for="name1_field">* Guest #1 name :</label> <input
							id="name1_field" name="name1" type="text" />
					</div>
					<div class="join_element">
						<label for="task1_select">* Guest #1 task :</label> <select
							id="task1_select" name="task1">
							<%
								for (Task t : tasks) {
							%>
									<option value="<%=t.getId()%>"><%=t.getName()%></option>
								<%
									}
								%>
						</select>
					</div>
					<input type="button" value="Remove guest"
						onclick="javascript:hideElement('join1'); document.getElementById('name1_field').value='';" />
				</div>
				<div id="join2" class="join supp">
					<div class="join_element">
						<label for="name2_field">* Guest #2 name : <input
							id="name2_field" name="name2" type="text" />
						</label>
					</div>
					<div class="join_element">
						<label for="task1_select">* Guest #2 task :</label> <select
							id="task2_select" name="task2">
							<%
								for (Task t : tasks) {
							%>
							<option value="<%=t.getId()%>"><%=t.getName()%></option>
							<%
								}
							%>
						</select>
					</div>
					<input type="button" value="Remove guest"
						onclick="javascript:hideElement('join2'); document.getElementById('name2_field').value='';" />
				</div>
				<div id="pay" class="join">
					<div class="join_element">
						<label for="email">* E-mail :</label> <input id="email"
							name="email" type="text" />
					</div>
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
					<br /> <br /> <input type="submit" value="Join" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>