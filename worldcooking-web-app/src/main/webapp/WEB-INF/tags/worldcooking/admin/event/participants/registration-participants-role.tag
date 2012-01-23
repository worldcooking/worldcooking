<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="eventRoles" required="true" type="java.util.List" %>
<%@ attribute name="participant" required="true" type="org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventParticipant" %>
<%@ attribute name="registrationId" required="true" type="java.lang.Long" %>
<%@ attribute name="registrationValidated" required="true" type="java.lang.Boolean" %>


<form action="${pageContext.request.contextPath}/direct/admin/event/update/role">
	<input class="participantId" type="hidden" name="participantId" value="${participant.id}" />
	<input class="amountHtmlId" type="hidden" name="amountHtmlId" value="amount_${registrationId}" />
	<c:if test="${not registrationValidated}">
		<!-- validated registration can be edited -->
		<select class="tasksSelect" name="eventRoleId">
		<c:forEach items="${eventRoles}" var="eventRole">
			<c:if test="${participant.task.id == eventRole.id}">
				<option value="${eventRole.id}" selected="selected">${eventRole.role.name}</option>
			</c:if>
			<c:if test="${participant.task.id != eventRole.id}">
				<option value="${eventRole.id}">${eventRole.role.name}</option>
			</c:if>
		</c:forEach>
		</select>
	</c:if>
	<c:if test="${registrationValidated}">
		<!-- validated registration can NOT be edited -->
		<c:forEach items="${eventRoles}" var="eventRole">
			<c:if test="${participant.task.id == eventRole.id}">
				${eventRole.role.name}
			</c:if>
		</c:forEach>
	</c:if>
</form>