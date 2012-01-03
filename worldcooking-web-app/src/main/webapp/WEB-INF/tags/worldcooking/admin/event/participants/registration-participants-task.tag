<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="tasks" required="true" type="java.util.List" %>
<%@ attribute name="participant" required="true" type="org.worldcooking.web.worldcooking.admin.event.participants.model.WorldcookingAdminEventParticipant" %>
<%@ attribute name="registrationId" required="true" type="java.lang.Long" %>


<form action="${pageContext.request.contextPath}/direct/admin/event/update/task">
	<input class="participantId" type="hidden" name="participantId" value="${participant.id}" />
	<input class="amountHtmlId" type="hidden" name="amountHtmlId" value="amount_${registrationId}" />
	<select class="tasksSelect" name="taskId">
		<c:forEach items="${tasks}" var="task">
			<c:if test="${participant.task.id == task.id}">
				<option value="${task.id}" selected="selected">${task.name}</option>
			</c:if>
			<c:if test="${participant.task.id != task.id}">
				<option value="${task.id}">${task.name}</option>
			</c:if>
		</c:forEach>
	</select>
</form>