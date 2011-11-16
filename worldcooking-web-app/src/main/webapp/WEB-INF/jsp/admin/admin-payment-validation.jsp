<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Administration</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/administration.css"
	media="screen" title="bbxcss" />
</head>
<body>
	<div class="main_chapter">
		<h2>Reset database</h2>
		<form:form commandName="paymentValidation">
			<div class="admin">
				<div class="admin_element">
					<label for="registrationId">* Registration payment to validate :</label>
					<form:select id="registrationId" path="registrationId" items="${registrations}" multiple="false" />
					
					<label for="pwd">* Password :</label>
					<form:input id="pwd" path="password" />
					<br/><form:errors path="password" cssClass="error" lang="en" />
				</div>
				<br />
				<br />
				<div class="join_element">
					<input type="submit" value="Validate payment" />
				</div>
			</div>
		</form:form>
	</div>

</body>
</html>