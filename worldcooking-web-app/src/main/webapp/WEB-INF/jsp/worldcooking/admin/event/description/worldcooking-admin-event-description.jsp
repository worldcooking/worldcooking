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
	<template-form:hidden path="eventName"/>
	
	<%-- event name --%>
	<template-form:input id="eventNameInput" path="eventName" label="Name"/>
	
	<%-- place --%>
	<template-form:select id="eventPlaceInput" path="placeId" label="Place" items="${places}" multiple="false"/>
	
	<%-- date --%>
	<template-form:input id="eventDateInput" path="date" label="Date"/>
	
	<%-- time --%>
	<template-form:input id="eventTimeInput" path="time" label="Time"/>
	
	<div style="clear:left"><input type="submit" value="Update" /></div>

</template-form:form>

<%-- 
<form:form id="eventDescriptionForm" commandName="eventDescriptionForm" action="${actionUrl}"  > 

<template:chapter >
	
	event id
	<form:hidden path="eventId"/>
	
	event name
	<label for="eventNameInput">Name :</label> 
	<form:input id="eventNameInput" path="eventName"  />
	<form:errors path="eventName"  cssClass="error" />
	
	place
	<br/>
	<label for="eventPlaceInput">Place :</label> 
	<form:select id="eventPlaceInput" path="placeId"  />   
	<form:errors path="placeId"  cssClass="error" />
	
	date
	<br/>
	<label for="eventDateInput">Date:</label> 
	<form:input id="eventDateInput" path="date"  />
	<form:errors path="date"  cssClass="error" />
	
	time
	<br/>
	<label for="eventTimeInput">Time:</label> 
	<form:input id="eventTimeInput" path="time"  />
	<form:errors path="time"  cssClass="error" />
	
	<br/><input type="submit" value="Update" />
</template:chapter> 
</form:form>--%>



<script type="text/javascript"> 
        $(document).ready(function() { 
            // bind 'myForm' and provide a simple callback function 
            $('#eventDescriptionForm').ajaxForm({ 
                target: '#Event_description'
            }); 
        }); 
    </script> 