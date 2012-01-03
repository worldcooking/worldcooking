<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<resources:require-css resourceURI="worldcooking/admin/worldcooking-admin-main.css"/>
	<title>Administration</title>
</head>
<body>
	<template:chapter title="Select an event to manage">
		<template:table>
			<jsp:attribute name="header">
				<th>date/time</th>
				<th>name</th>
				<th>place</th>
				<th>participants</th>
				<th>status</th>
				<th>languages</th>
			</jsp:attribute>
			<jsp:attribute name="content">
				<c:forEach items="${events}" var="event">
					<c:url var="eventAdminUrl" value="/admin/event/${event.reference}" />
					<tr>
						<td><fmt:formatDate value="${event.dateTime}" pattern="MM/dd/yyyy HH:mm"/></td>
						<td><a href="${eventAdminUrl}" class="eventAdminLink">${event.name}</a></td>
						
						<c:set var="place" value="${event.place.name}" />
						<c:if test="${not empty event.place.city or not empty event.place.country}">
							<c:if test="${not empty event.place.city and not empty event.place.country}">
								<!-- name (city, COUNTRY) -->
								<c:set var="place" value="${place} (${event.place.city}, ${event.place.country})" />
							</c:if>
							<c:if test="${empty event.place.city}">
								<!-- name (COUNTRY) -->
								<c:set var="place" value="${place} (${event.place.country})" />
							</c:if>
							<c:if test="${empty event.place.country}">
								<!-- name (city) -->
								<c:set var="place" value="${place} (${event.place.city})" />
							</c:if>
						</c:if>
						<td>${place}</td>
						<%-- participants count --%>
						<c:if test="${not empty event.participants}">
							<c:set var="participants" value="${event.participants.current}/${event.participants.max}" />
							
							<%-- add pending participants --%>
							<c:if test="${event.participants.pending != 0}">
								<c:set var="participants" value="${participants} (+ ${event.participants.pending} pending)" />
							</c:if>
						</c:if>
						<td>${participants}</td>
						<%-- event status --%>
						<td>${event.status}</td>
						<%-- list of available translations languages --%>
						<td>
						<c:forEach var="language" items="${event.languages}" varStatus="status"><c:if test="${status.index != 0}">, </c:if>${language}</c:forEach>
					</tr>
				</c:forEach>
			</jsp:attribute>
		</template:table>
	</template:chapter>

</body>
</html>