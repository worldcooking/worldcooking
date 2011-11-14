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
		<h2>Administration</h2>
		Select an event to manage:
		<ul><c:forEach items="${events}" var="event">
			<li><a href="${pageContext.request.contextPath}/admin/event?eventId=<c:out value="${event.id}"/>"><c:out value="${event.name}"/></a>
			<p style="max-height: 45px;overflow: scroll;"><c:out value="${event.description}" escapeXml="false" /></p></li>
		</c:forEach></ul>
	</div>

</body>
</html>