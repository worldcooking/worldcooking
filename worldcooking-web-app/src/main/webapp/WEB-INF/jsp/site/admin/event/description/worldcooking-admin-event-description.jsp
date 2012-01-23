<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.mishk.org/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.mishk.org/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.mishk.org/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"  %>
<%@ taglib prefix="template-form" uri="http://www.mishk.org/tags/template-form"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<c:url var="actionUrl" value="/direct/admin/event/${event.reference}/description" />

<template-form:form id="eventDescriptionForm" commandName="eventDescriptionForm" action="${actionUrl}" >
	<%-- event id --%>
	<form:hidden id="eventIdHiddenInput" path="eventId" />
		
	<%-- event name --%>
	<template-form:input id="eventNameInput" path="eventName" label="Name" required="true" newGroup="true"/>
	
	<%-- place --%>
	<template-form:select id="eventPlaceInput" path="placeId" label="Place" items="${places}" multiple="false"/>
	
	<%-- registration status --%>
	<template-form:select id="eventRegistrationStatusInput" path="eventRegistrationStatus" label="Registration status" items="${availableEventRegistrationStatus}" multiple="false"/>
	
	<%-- date --%>
	<template-form:input id="eventDateInput" path="date" label="Date" newGroup="true" size="small"/>
	
	<%-- time --%>
	<template-form:input id="eventTimeInput" path="time" label="Time" size="small"/>
	
	<template-form:submit title="Update event description" value="Update" newGroup="true" />
			
		<hr style="clear:both"/>
			
</template-form:form>

<script>
	 $(function() {
		$( "#eventDateInput" ).datepicker({ minDate: 0, dateFormat: "yy-mm-dd" });
	}); 
	</script>


<script type="text/javascript"> 
        $(document).ready(function() { 
            // bind 'myForm' and provide a simple callback function 
            $('#eventDescriptionForm').ajaxForm({ 
                target: '#Event_description'
            }); 
        });  
    </script> 