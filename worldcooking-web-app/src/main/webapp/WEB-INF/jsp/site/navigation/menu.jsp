<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasAnyRole('ADMIN','SUPER_ADMIN')">

	<template:chapter title="Menu" cssClass="menu">
	<ul>
			<c:url 	var="homeUrl" value="/" />
			<li><a href="${homeUrl}">home</a></li>
	</ul>			
	</template:chapter>

	<template:chapter title="Admin" cssClass="menu">
		<ul>
			<c:url 	var="manageEventsUrl" value="/admin/events" />
			<li><a href="${manageEventsUrl}">events</a></li>
			
			<sec:authorize access="hasRole('SUPER_ADMIN')">
				<c:url 	var="manageUsersUrl" value="/admin/security/manage/users" />
				<li><a href="${manageUsersUrl}">users</a></li>
			
				<c:url 	var="adminDbUrl" value="/admin/db" />
				<li><a href="${adminDbUrl}">database</a></li>
			
				<c:url 	var="adminShopUrl" value="/admin/shop" />
				<li><a href="${adminShopUrl}">shop</a></li>
	
			</sec:authorize>
		</ul>
	</template:chapter>

</sec:authorize>