<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="eventRoles" required="true" type="java.util.List" %>
<%@ attribute name="participant" required="true" type="org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventParticipant" %>
<%@ attribute name="registrationId" required="true" type="java.lang.Long" %>


<form action="${pageContext.request.contextPath}/direct/admin/event/update/task">
	<input class="participantId" type="hidden" name="participantId" value="${participant.id}" />
	<input class="amountHtmlId" type="hidden" name="amountHtmlId" value="amount_${registrationId}" />
	<select class="tasksSelect" name="taskId">
		<c:forEach items="${eventRoles}" var="eventRole">
			<c:if test="${participant.task.id == eventRole.id}">
				<option value="${eventRole.id}" selected="selected">${eventRole.role.name}</option>
			</c:if>
			<c:if test="${participant.task.id != eventRole.id}">
				<option value="${eventRole.id}">${eventRole.role.name}</option>
			</c:if>
		</c:forEach>
	</select>
</form>