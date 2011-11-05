<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>${event.name} - Paypal payment</title>
</head>
<body>
	<div class="main_chapter">
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
			<input type="submit" value="PAYPAL" />
		</form>
	</div>
</body>
</html>