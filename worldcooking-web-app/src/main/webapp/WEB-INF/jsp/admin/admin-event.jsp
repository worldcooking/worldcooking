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
		<h2>Administration of '<c:out value="${event.name}"/>' event</h2>
		<h3>List of validated registrations</h3>
		<ul><c:forEach items="${validatedRegistrations}" var="registration">
			<li><b><c:out value="${registration.registrer.name}"/> - <c:out value="${registration.registrer.taskName}"/> (<c:out value="${registration.registrer.email}"/>, <c:out value="${registration.amount}"/> €)</b> 
				[<a href="${pageContext.request.contextPath}/admin/event/unvalidate/registration?registrationId=<c:out value="${registration.id}"/>">unvalidate</a>]
				<c:if test="${!registration.additionalParticipants.isEmpty()}">
				<br/><i>Additional participants:</i>
					<ul><c:forEach items="${registration.additionalParticipants}" var="participant">
					<li><c:out value="${participant.name}"/> - <c:out value="${participant.taskName}"/></li>
					</c:forEach></ul>
				</c:if>
			</li>
		</c:forEach></ul>
		
		<h3>List of non-validated registrations</h3>
		<ul><c:forEach items="${nonValidatedRegistrations}" var="registration">
			<li><b><c:out value="${registration.registrer.name}"/> - <c:out value="${registration.registrer.taskName}"/> (<c:out value="${registration.registrer.email}"/>, <c:out value="${registration.amount}"/> €)</b> 
				[<a href="${pageContext.request.contextPath}/admin/event/validate/registration?registrationId=<c:out value="${registration.id}"/>">validate</a>]
				<c:if test="${!registration.additionalParticipants.isEmpty()}">
				<br/><i>Additional participants:</i>
					<ul><c:forEach items="${registration.additionalParticipants}" var="participant">
					<li><c:out value="${participant.name}"/> - <c:out value="${participant.taskName}"/></li>
					</c:forEach></ul>
				</c:if>
			</li>
		</c:forEach></ul>
	</div>
</body>
</html>