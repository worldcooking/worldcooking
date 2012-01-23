<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.mishk.org/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.mishk.org/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.mishk.org/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"  %>
<%@ taglib prefix="tabs" uri="http://www.mishk.org/tags/template-tabs"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>${event.name} - administration</title>
	<jquery:require-static-resources />
	<jquery-ui:require-static-resources />
	<resources:require-css resourceURI="site/admin/event/worldcooking-admin-event.css"/>
	<resources:require-script resourceURI="site/history/worldcooking-history.js" />
	<resources:require-script resourceURI="site/admin/event/worldcooking-admin-event.js" />
	<resources:require-script resourceURI="site/admin/event/worldcooking-admin-event_update-role.js" />
	<resources:require-script resourceURI="site/admin/event/worldcooking-admin-event_validate.js" />
</head>
<body>

<c:url var="eventAdminDescriptionUrl" value="/direct/admin/event/${event.reference}/description" />
<c:url var="eventAdminTasksUrl" value="/direct/admin/event/${event.reference}/tasks" />
<c:url var="eventAdminParticipantsUrl" value="/direct/admin/event/${event.reference}/participants" />
							
<tabs:menu>
	<tabs:entry title="Event description" ajaxUrl="${eventAdminDescriptionUrl}" />
	<tabs:entry title="Event tasks" ajaxUrl="${eventAdminTasksUrl}" />
	<tabs:entry title="Participants" ajaxUrl="${eventAdminParticipantsUrl}" />
</tabs:menu>

</body>
</html>