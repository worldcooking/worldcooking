<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<html>
<head>
	<title>Worldcooking - administration</title>
	<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
</head>
<body>
<template:chapter title="Reset database">
	<form:form commandName="administrationResetDb">
		<div class="admin">
			<br />
			<div class="join_element">
				<input type="submit" value="Reset Data Base" />
			</div>
		</div>
	</form:form>
</template:chapter>

</body>
</html>