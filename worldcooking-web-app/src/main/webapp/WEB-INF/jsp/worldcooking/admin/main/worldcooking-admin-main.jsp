<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>

<html>
<head>
	<resources:require-css resourceURI="worldcooking/worldcooking-main.css"/>
	<resources:require-css resourceURI="worldcooking/admin/worldcooking-admin-main.css"/>
	<title>Administration</title>
</head>
<body>
	<template:chapter>
		<jsp:attribute name="title">Select an event to manage</jsp:attribute>
		<jsp:body>
		<table class="table">
			<thead>
				<tr>
					<th>name</th>
					<th>description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${events}" var="event">
					<tr>
						<td><a href="${pageContext.request.contextPath}/admin/event?eventId=<c:out value="${event.id}"/>"><c:out
									value="${event.name}" /></a></td>
						<td>${event.description}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</jsp:body>
	</template:chapter>

</body>
</html>