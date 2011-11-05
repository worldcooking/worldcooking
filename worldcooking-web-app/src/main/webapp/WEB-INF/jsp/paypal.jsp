<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${event.name} - Paypal payment</title>
</head>
<body onload="javascript:cleanInputs(['name1_field','name2_field']);">
	<div class="main_chapter">
		<!--  <form action="https://www.paypal.com/cgi-bin/webscr" method="post"> -->
		<form name="paypalForm"
			action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
			<input type="hidden" name="cmd" value="_xclick"> <input
				type="hidden" name="business"
				value="potcom_1320018938_biz@gmail.com"> <input
				type="hidden" name="item_name" value="peru">
			<input type="hidden" name="item_number" value="1"> <input
				type="hidden" name="amount" value="15.00"> <input
				type="hidden" name="no_shipping" value="0"> <input
				type="hidden" name="no_note" value="1"> <input type="hidden"
				name="currency_code" value="CAD"> <input type="hidden"
				name="return" value="http://localhost:8080/worldcooking-web-app/">
			<input type="hidden" name="lc" value="AU"> <input
				type="hidden" name="bn" value="PP-BuyNowBF"> <input
				type="submit" value="PAYPAL" />
		</form>
	</div>
</body>
</html>