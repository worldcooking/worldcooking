<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="web-security" uri="http://www.oups-asso.org/mish-k/tags/web-security"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<web-security:login-area />

<sec:authorize access="hasAnyRole('ADMIN','SUPER_ADMIN')">

	<template:chapter title="Admin" cssClass="menu">
		<ul>
			<c:url 	var="manageEventsUrl" value="/admin/event" />
			<li><a href="${manageEventsUrl}">events</a></li>
			
			<sec:authorize access="hasRole('SUPER_ADMIN')">
				<c:url 	var="manageUsersUrl" value="/admin/security/manage/users" />
				<li><a href="${manageUsersUrl}">users</a></li>
			
				<c:url 	var="adminDbUrl" value="/admin/db" />
				<li><a href="${adminDbUrl}">database</a></li>
			
			</sec:authorize>
		</ul>
	</template:chapter>

</sec:authorize>