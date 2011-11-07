<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>${event.name} - Paypal & CB payment</title>
</head>
<body onload="javascript:document.getElementById('paypalFormId').submit()">
		<div class="main_chapter">
			<h2>Paypal & CB payment</h2>
			<p>
				If you're not automatically redirected to the Paypal payment page, please click on the button below.<br/>
				<br/>
				<form name="paypalForm" action="${paypalFormAction}" id="paypalFormId" >
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
			<input type="submit" value="Go to paypal Web site to finalize my registration." title="WTF?! you're not redirected?!"/>
		</form>
			</p>
		</div> 
</body>
</html>