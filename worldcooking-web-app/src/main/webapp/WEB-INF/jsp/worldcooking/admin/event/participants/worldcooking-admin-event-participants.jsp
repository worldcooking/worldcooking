<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="worldcooking-admin" tagdir="/WEB-INF/tags/worldcooking/admin/event/participants"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- AJAX URLS -->
<c:url var="updateTaskUrl" value="/direct/admin/event/${event.reference}/update/task"/>
<input  id="updateTaskAjaxUrl" type="hidden" name="updateTaskUrl" value="${updateTaskUrl}" />

<c:url var="showValidatedRegistrationsUrl" value="/direct/admin/event/${event.reference}/validated/registrations"/>
<input  id="showAdminValidatedRegistrationsAjaxUrl" type="hidden" name="showAdminValidatedRegistrationsAjaxUrl" value="${showValidatedRegistrationsUrl}" />

<c:url var="showUnvalidatedRegistrationsUrl" value="/direct/admin/event/${event.reference}/unvalidated/registrations"/>
<input  id="showAdminUnvalidatedRegistrationsAjaxUrl" type="hidden" name="showAdminUnvalidatedRegistrationsAjaxUrl" value="${showUnvalidatedRegistrationsUrl}" />


<!-- TODO dynamic auto update from server push (and add update to history: 'List of participants updated by Peter at 8:30 [open a chat with him]') -->

<template:chapter >
	<div id="validated_registrations">
		<worldcooking-admin:registration-participants registrations="${validatedRegistrations}" />
	</div>

	<div id="unvalidated_registrations">
		<worldcooking-admin:registration-participants registrations="${nonValidatedRegistrations}" />
	
	</div>
	<div id="actionsHistory">
		<jsp:include page="/WEB-INF/jsp/history.jsp" />
	</div>
</template:chapter>

