<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>${event.name} - administration</title>
	<jquery:require-static-resources />
	<jquery-ui:require-static-resources />
	<resources:require-css resourceURI="worldcooking/admin/event/worldcooking-admin-event.css"/>
	<resources:require-script resourceURI="worldcooking/history/worldcooking-history.js" />
	<resources:require-script resourceURI="worldcooking/admin/event/worldcooking-admin-event.js" />
	<resources:require-script resourceURI="worldcooking/admin/event/worldcooking-admin-event_update-task.js" />
	<resources:require-script resourceURI="worldcooking/admin/event/worldcooking-admin-event_validate.js" />
	<resources:require-script resourceURI="3rdparty/form/jquery.form.js" locationType="JAR" />
</head>
<body>

<template:chapter title="Administration of '${event.name}' event">
	<div id="tabs" class="mishk_jquery-ui_tabs">
		<ul>
			<%-- description tab --%>
			<c:url var="eventAdminDescriptionUrl" value="/direct/admin/event/${event.reference}/description" />
			<li><a href="${eventAdminDescriptionUrl}" title="Event description">Event description</a></li>
			
			<%-- tasks tab --%>
			<c:url var="eventAdminTasksUrl" value="/direct/admin/event/${event.reference}/tasks" />
			<li><a href="${eventAdminTasksUrl}" title="Event tasks">Event tasks</a></li>
			
			<%-- participants tab --%>
			<c:url var="eventAdminParticipantsUrl" value="/direct/admin/event/${event.reference}/participants" />
			<li><a href="${eventAdminParticipantsUrl}" title="Participants">Participants</a></li>
		<br/><br/><br/>TOTO
		</ul>
		
		<div id="Event_description"></div>
			<div id="Event_tasks"></div>
			<div id="Participants"></div>
	</div>
</template:chapter>


<script>
	$(function() {
		$( ".mishk_jquery-ui_tabs" ).tabs({
			cookie: {
				expires: 30
			},
			ajaxOptions: {
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"Couldn't load this tab. Please refresh the page or try later." );
				}
			}
		});
	});
	</script>


</body>
</html>