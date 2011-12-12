<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<html>
<head>
	<jquery:require-static-resources />
	<resources:require-css resourceURI="worldcooking/worldcooking-main.css"/>
	<resources:require-script resourceURI="worldcooking/registration/confirmation/worldcooking-paypal-confirmation.js" />
	<title>${event.name} - Paypal & CB payment</title>
</head>
<body>
	<template:chapter title="Paypal & CB payment">
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
	<input type="hidden" name="lc" value="EN" /> 
	<input type="hidden" name="bn" value="PP-BuyNowBF"/> 
	<input type="submit" value="Go to paypal Web site to finalize my registration." title="WTF?! you're not redirected?!"/>
</form>
			</p>
	</template:chapter>
</body>
</html>