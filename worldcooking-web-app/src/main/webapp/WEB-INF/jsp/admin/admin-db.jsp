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
		<form:form commandName="administrationResetDb">
			<div class="admin">
				<div class="admin_element">
					<label for="pwd">* Password :</label>
					<form:input id="pwd" path="password" />
					<form:errors path="password" cssClass="error" lang="en" />
				</div>
				<br />
				<br />
				<div class="join_element">
					<input type="submit" value="Reset Data Base" />
				</div>
			</div>
		</form:form>
	</div>

</body>
</html>