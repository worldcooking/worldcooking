<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="template-form" uri="http://www.oups-asso.org/mish-k/tags/template-form"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<c:url var="actionUrl" value="/direct/admin/event/${event.reference}/description" />

<template-form:form id="eventDescriptionForm" commandName="eventDescriptionForm" action="${actionUrl}" >

	<%-- event id --%>
	<form:hidden id="eventIdHiddenInput" path="eventId" />
		
	<%-- event name --%>
	<template-form:input id="eventNameInput" path="eventName" label="Name" required="true" newGroup="true"/>
	
	<%-- place --%>
	<template-form:select id="eventPlaceInput" path="placeId" label="Place" items="${places}" multiple="false"/>
	
	<%-- date --%>
	<template-form:input id="eventDateInput" path="date" label="Date" newGroup="true" size="small"/>
	
	<%-- time --%>
	<template-form:input id="eventTimeInput" path="time" label="Time" size="small"/>
	
	<template-form:submit title="Update event description" value="Update" newGroup="true" />
			
		<span style="clear:both">.</span>
			
</template-form:form>

<script>
	 $(function() {
		$( "#eventDateInput" ).datepicker({ minDate: 0, dateFormat: "yy-mm-dd" });
		$( "#eventTimeInput" ).timepicker({timeFormat: 'hh:mm'});
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