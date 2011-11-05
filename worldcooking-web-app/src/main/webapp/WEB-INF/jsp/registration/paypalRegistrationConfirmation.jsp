<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>${event.name} - Paypal payment</title>
</head>
<body>
		<div class="main_chapter">
			<h2>Congratulations!</h2>
			<p>
				You successfully process to your pre-registration!<br/>
				We will send an email to confirm your registration once we receive your payment.<br/>
				<br/>
				<form name="paypalForm" action="${paypalFormAction}"  >
			<input type="hidden" name="cmd" value="_xclick"/> 
			<input type="hidden"  name="business" value="${paypalBusinessEmailAddress}"/>
			<input type="hidden" name="business" value="${paypalBusinessEmailAddress}"/> 
			<input type="hidden" name="item_name" value="${paypalItemName}"/> 
			<input type="hidden" name="item_number" value="${paypalItemNumber}"/> 
			<input type="hidden" name="amount" 
				value="<fmt:formatNumber pattern="#.##" >${paypalAmount}</fmt:formatNumber>"/> 
			<input type="hidden" name="no_shipping" value="0"/> 
			<input type="hidden" name="no_note" value="1"/> 
			<input type="hidden" name="currency_code" value="${paypalCurrencyCode}"/> 
			<input type="hidden" name="return" value="${paypalReturnUrl}"> 
			<input type="hidden" name="lc" value="AU" /> 
			<input type="hidden" name="bn" value="PP-BuyNowBF"/> 
			<input type="submit" value="Go to paypal Web site to finalize my registration." />
		</form>
			</p>
		</div> 
</body>
</html>