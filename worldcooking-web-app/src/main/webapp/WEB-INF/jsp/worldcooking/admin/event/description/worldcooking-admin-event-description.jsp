<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<form:form commandName="eventDescriptionForm"   > 

<template:chapter >
	
	<%-- event id --%>
	<form:hidden path="eventId"/>
	
	<%-- event name --%>
	<label for="eventNameInput">Name :</label> 
	<form:input id="eventNameInput" path="eventName"  />
	<form:errors path="eventName"  cssClass="error" />
	
	<%-- place --%>
	<br/>
	<label for="eventPlaceInput">Place :</label> 
	<form:select id="eventPlaceInput" path="placeId" items="${places}" multiple="false" />   
	<form:errors path="placeId"  cssClass="error" />
	
	<%-- date --%>
	<br/>
	<label for="eventDateInput">Date:</label> 
	<form:input id="eventDateInput" path="date"  />
	<form:errors path="date"  cssClass="error" />
	
	<%-- time --%>
	<br/>
	<label for="eventTimeInput">Time:</label> 
	<form:input id="eventTimeInput" path="time"  />
	<form:errors path="time"  cssClass="error" />
	
	<br/><input type="submit" value="Update" />
</template:chapter>

</form:form>
