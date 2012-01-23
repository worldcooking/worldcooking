<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="template-form" uri="http://www.oups-asso.org/mish-k/tags/template-form"  %>
<html>
<head>
	<title>${event.name} - Pre-registration confirmation</title>
</head>
<body>
	<template:chapter title="Warning : your place is still not reserved!">
		<p>
			<b>Please pay as soon as possible, before all the available places are all gone.</b><br/><br/>

			There's a limit of place available, and if you're paying too late, we will not be able to reserve you a place<br/>
			We will send an email to confirm your registration once we receive your payment.<br/>
			<br/>
			<template-form:button href="<c:url value='/'/>" title="Go back to ${event.name} main page" >
				Go back to ${event.name} main page
			</template-form:button>
		<br/>
		</p>
	</template:chapter>
</body>
</html>