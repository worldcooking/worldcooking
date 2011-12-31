<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>

<html>
<head>
	<resources:require-css resourceURI="worldcooking/admin/worldcooking-admin-main.css"/>
	<title>Administration</title>
</head>
<body>
	<template:chapter title="Select an event to manage">
		<template:table>
			<jsp:attribute name="header">
				<th>name</th>
				<th>description</th>
			</jsp:attribute>
			<jsp:attribute name="content">
				<c:forEach items="${events}" var="event">
					<tr>
						<td><a href="${pageContext.request.contextPath}/admin/event?eventId=<c:out value="${event.id}"/>"><c:out
									value="${event.name}" /></a></td>
						<td>${event.description}</td>
					</tr>
				</c:forEach>
			</jsp:attribute>
		</template:table>
	</template:chapter>

</body>
</html>