<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resources" uri="http://www.mishk.org/tags/resources"  %>
<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"  %>
<%@ taglib prefix="template-form" uri="http://www.mishk.org/tags/template-form"  %>
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
			<c:url var="mainPage" value='/'/>
			<template-form:button href="${mainPage}" title="Go back to ${event.name} main page" >
				Go back to ${event.name} main page
			</template-form:button>
		<br/>
		</p>
	</template:chapter>
</body>
</html>