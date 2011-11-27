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
		<h2>Select an event to manage</h2>
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
	</div>

</body>
</html>