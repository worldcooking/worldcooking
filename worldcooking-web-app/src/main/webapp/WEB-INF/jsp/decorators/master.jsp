<!-- Maket for worldcooking subscription. -->
<head>
	<title>Worldcooking Peru</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" title="bbxcss" />
	<style type="text/css">
	</style>
	<script type="text/javascript" src="js/main.js">
	</script>
</head>
<body onload="javascript:cleanInputs(['name1_field','name2_field']);">
	<div class="header">
		<div class="header_title">
			<h1>Worlcooking Peru</h1>
		</div>
	</div>

	<div id="main" class="main">
		<div class="main_chapter">
			<h2>Informations</h2>
				<p>Up to 38 persons will share a peruvian meal in the restaurant La soupe au Caillou.<br/>
				Our chef will be Nidia Torres.</br>
				</br>
				To participate in this event you must book and pay in advance.</br>
				The price of Sorie is 15 € per person. This amount is used entirely to cover the cost of the evening.</br>
				</br>
				This year we ask each person to help. When registering you can choose a task from the following:</br>
				- Cooking with Nidia from 4pm</br>
				- Set the table</br>
				- Doing the dishes</br>
				- Cleaning the room</br>
				</p>
				<div class="information">
					<h3>Menu :</h3>
					- Causa Rellena<br/>
					- Lomo Saltado<br/>
					- Mazamorra Morada<br/>
				</div>
				<div class="information">
					<h3>Date & Time :</h3>
					Friday November 25th 2011, 8pm (4pm if you're cooking)
				</div>
				<div class="information">
					<h3>Place :</h3>
					<div class="adress" onmouseover="showElement('googlemap')"  onmouseout="hideElement('googlemap')" >
						<b>La soupe au Caillou</b><br/>
						15 Rue Charles Gounod<br/>
						31200 Toulouse<br/>
						<a href="http://maps.google.fr/maps?f=q&amp;source=embed&amp;hl=fr&amp;geocode=&amp;q=la+soupe+aux+caillou&amp;aq=&amp;gl=fr&amp;g=15+Rue+Charles+Gounod,+31200+Toulouse&amp;ie=UTF8&amp;hq=la+soupe+aux+caillou&amp;hnear=Toulouse,+Haute-Garonne,+Midi-Pyr%C3%A9n%C3%A9es&amp;t=m&amp;vpsrc=0&amp;z=14&amp;iwloc=A&amp;cid=12869456708502713567&amp;ll=43.613686,1.424208" target="_blank">Voir la carte</a>
						<iframe class="carte" id="googlemap"
						src="http://maps.google.fr/maps?q=soupe+au+caillou+toulouse&amp;oe=utf-8&amp;client=firefox-a&amp;ie=UTF8&amp;hl=fr&amp;hq=soupe+au+caillou&amp;hnear=Toulouse,+Haute-Garonne,+Midi-Pyr%C3%A9n%C3%A9es&amp;ll=43.613686,1.424208&amp;spn=0.019875,0.044002&amp;t=m&amp;vpsrc=6&amp;output=embed">
						</iframe>
					</div>
					
				</div>
		<div class="main_chapter">
			<h2>Participants</h2>	
			<div name="div_participants" class="participants">
				<table class="participants">
					<thead>
						<tr>
							<th>Participant (4/38)</th>
							<th>Chef (4/38)</th>
							<th>Cooking (4/38)</th>
							<th>Setting the table (4/38)</th>
							<th>Doing the dishes (4/38)</th>
							<th>Cleaning the room (4/38)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Matthieu Gaudet</th>
							<td><input type="radio" name="task0" value="chief" checked="checked"/></td>
							<td><input type="radio" name="task0" disabled="disabled" value="cooking"/></td>
							<td><input type="radio" name="task0" disabled="disabled" value="setting"/></td>
							<td><input type="radio" name="task0" disabled="disabled" value="dishes"/></td>
							<td><input type="radio" name="task0" disabled="disabled" value="cleaning"/></td>
						</tr>
						<tr>
							<th>Maya Rouvneska</th>
							<td><input type="radio" name="task1" disabled="disabled" value="chief"/></td>
							<td><input type="radio" name="task1" value="cooking" checked="checked"/></td>
							<td><input type="radio" name="task1" disabled="disabled" value="setting"/></td>
							<td><input type="radio" name="task1" disabled="disabled" value="dishes"/></td>
							<td><input type="radio" name="task1" disabled="disabled" value="cleaning"/></td>
						</tr>
						<tr>
							<th>Nicolas Toublanc</th>
							<td><input type="radio" name="task2" disabled="disabled" value="chief"/></td>
							<td><input type="radio" name="task2" disabled="disabled" value="cooking"/></td>
							<td><input type="radio" name="task2" value="setting" checked="checked" /></td>
							<td><input type="radio" name="task2" disabled="disabled" value="dishes"/></td>
							<td><input type="radio" name="task2" disabled="disabled" value="cleaning"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="main_chapter">
			<h2>Join us!</h2>
			<p>The price for this meal is <b>15 € per person.</b><br/>
				This amount can pay the rent of the room, the ingredients for the meal and drinks.<br/>
				To pay you can use paypal. In this way the registration is immediate.<br/>
				You can also pay directly to Matthieu, Nidia, Ben, Nicolas or Toub, but your registration will be validated only when you give them the money.<br/>
			</p>
			<form>
				<div id="join0" class="join">
					<div class="join_element">
						<label for="name0_field">* Your name :
							<input id="name0_field" name="name0" type="text"/>
						</label>
					</div>
					<div class="join_element">
						<label for="task0_select">* Your task :</label>
						<select id="task0_select" name="task0">
							<option value="chief" disabled="disabled">Chief</option>
							<option value="cooking" selected>Cooking</option>
							<option value="setting">Setting the table</option>
							<option value="dishes">Dishes</option>
							<option value="cleaning">Cleaning</option>
						</select>
					</div>
					<input type="button" value="Add a guest"  onclick="javascript:showOneMoreElement(['join1','join2'], 'Vous ne pouvez inscrire que 3 personnes.');"/>
				</div>
				<div id="join1" class="join supp">
					<div class="join_element">
						<label for="name1_field">* Guest #1 name :</label>
						<input id="name1_field" name="name1" type="text"/>
					</div>
					<div class="join_element">
						<label for="task1_select">* Guest #2 task :</label>
						<select id="task1_select" name="task1">
							<option value="chief" disabled="disabled">Chief</option>
							<option value="cooking" selected>Cooking</option>
							<option value="setting">Setting the table</option>
							<option value="dishes">Dishes</option>
							<option value="cleaning">Cleaning</option>
						</select>
					</div>
					<input type="button" value="Remove guest" onclick="javascript:hideElement('join1'); document.getElementById('name1_field').value='';"/>
				</div>
				<div id="join2"  class="join supp">
					<div class="join_element">
						<label for="name2_field">* Guest #2 name :
						<input id="name2_field" name="name2" type="text"/>
						</label>
					</div>
					<div class="join_element">
						<label for="task1_select">* Guest #2 task :</label>
						<select id="task2_select" name="task2" >
							<option value="chief" disabled="disabled">Chief</option>
							<option value="cooking" selected>Cooking</option>
							<option value="setting">Setting the table</option>
							<option value="dishes">Dishes</option>
							<option value="cleaning">Cleaning</option>
						</select>
					</div>
					<input type="button" value="Remove guest" onclick="javascript:hideElement('join2'); document.getElementById('name2_field').value='';"/>
				</div>
				<div id="pay" class="join">
					<div class="join_element">
						<label for="email">* E-mail :</label>
						<input id="email" name="email" type="text"/>
					</div>
					<div class="join_element">
						<label for="paiement_mode">* Paiement :</label>
						<select id="paiement_mode">
							<option value="paypal">Paypal</option>
							<option value="mgaudet">Pay to Matthieu Gaudet</option>
							<option value="ngruyer">Pay to Nicolas Gruyer</option>
							<option value="ntoublanc">Pay to Nicolas Toublanc</option>
							<option value="ntorres">Pay to Nidia Torres</option>
						</select>
					</div>
					<br/><br/>
					<input type="submit" value="Join"/>
				</div>
			</form>
		</div>
	</div>
</body>