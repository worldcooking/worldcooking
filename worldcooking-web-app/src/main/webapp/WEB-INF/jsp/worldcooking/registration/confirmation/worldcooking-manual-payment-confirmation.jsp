<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<html>
<head>
	<title>${event.name} - Pre-registration confirmation</title>
</head>
<body>
	<template:chapter title="Warning!">
		<p>
			<b>Warning : your place is still not reserved!</b><br/>
			Please pay as soon as possible, before the places available are all gone.<br/><br/>

			There's a limit of place available, and if you're paying too late, we will not be able to reserve you a place<br/>
			We will send an email to confirm your registration once we receive your payment.<br/>
			<br/>
			<a href="${pageContext.request.contextPath}">Go back to ${event.name} main page</a>
		</p>
	</template:chapter>
</body>
</html>