<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<html>
<head>
<title>Worldcooking '${event.name}' administration</title>
	<jquery:require-static-resources />
	<jquery-ui:require-static-resources />
	<resources:require-css resourceURI="worldcooking/worldcooking-main.css"/>
	<resources:require-css resourceURI="worldcooking/admin/event/worldcooking-admin-event.css"/>
	<resources:require-script resourceURI="worldcooking/history/worldcooking-history.js" />
	<resources:require-script resourceURI="worldcooking/admin/event/worldcooking-admin-event.js" />
	<resources:require-script resourceURI="worldcooking/admin/event/worldcooking-admin-event_update-task.js" />
	<resources:require-script resourceURI="worldcooking/admin/event/worldcooking-admin-event_validate.js" />
	
</head>
<body>

<!-- AJAX URLS -->
<input  id="updateTaskAjaxUrl" type="hidden" name="updateTaskUrl" value="${pageContext.request.contextPath}/direct/admin/event/update/task" />


<!-- TODO dynamic auto update from server push (and add update to history: 'List of participants updated by Peter at 8:30 [open a chat with him]') -->

<template:chapter>
	<jsp:attribute name="title">Administration of '${event.name}' event</jsp:attribute>
		<jsp:body>

		<div id="validated_registrations">
			<c:if test="${!validatedRegistrations.isEmpty()}">
				<h3>List of validated registrations</h3>
				<template:table cssClass="table participants">
					<jsp:attribute name="header">
						<th>name</th>
						<th>task</th>
						<th>email</th>
						<th>amount</th>
						<th>payment method</th>
						<th>actions</th>
					</jsp:attribute>
					<jsp:attribute name="content">
						<c:forEach items="${validatedRegistrations}" var="registration">
							<c:set var="rowspan" value="${registration.additionalParticipants.size() + 1}" />
							<tr class="participant">
								<td>${registration.registrer.participant.name}</td>
								<td>
										<form action="${pageContext.request.contextPath}/direct/admin/event/update/task">
											<input  class="participantId" type="hidden" name="participantId" value="${registration.registrer.participant.id}" />
											<select class="tasksSelect" name="taskId">
												<c:forEach items="${tasks}" var="task" >
													<c:if test="${registration.registrer.participant.task.id == task.id}">
														<option value="${task.id}"  selected="selected">${task.name}</option>
													</c:if>
													<c:if test="${registration.registrer.participant.task.id != task.id}">
														<option value="${task.id}">${task.name}</option>
													</c:if>
												</c:forEach>
											</select>
										</form>
									</td>
								<td rowspan="${rowspan}">${registration.registrer.email}</td>
								<td rowspan="${rowspan}">${registration.amount} €</td>
								<td rowspan="${rowspan}">payed to ${registration.paymentDescription}</td>
								<td rowspan="${rowspan}">
									<form action="${pageContext.request.contextPath}/admin/event/unvalidate/registration">
										<input type="hidden" name="registrationId" value="${registration.id}" /> <input type="submit"
											value="unvalidate" class="unvalidateButton" />
									</form>
								</td>
							</tr>
							<c:if test="${!registration.additionalParticipants.isEmpty()}">
								<c:forEach items="${registration.additionalParticipants}" var="participant">
									<tr class="participant">
										<td>${participant.name}</td>
										<td>
										<form action="${pageContext.request.contextPath}/direct/admin/event/update/task">
											<input  class="participantId" type="hidden" name="participantId" value="${participant.id}" />
											<select class="tasksSelect" name="taskId">
												<c:forEach items="${tasks}" var="task" >
													<c:if test="${participant.task.id == task.id}">
														<option value="${task.id}"  selected="selected">${task.name}</option>
													</c:if>
													<c:if test="${participant.task.id != task.id}">
														<option value="${task.id}">${task.name}</option>
													</c:if>
												</c:forEach>
											</select>
										</form>
									</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:forEach>
					</jsp:attribute>
				</template:table>
			</c:if>
		</div>

		<div id="unvalidated_registrations">
			<c:if test="${!nonValidatedRegistrations.isEmpty()}">
				<h3>List of NON validated registrations</h3>
				<template:table cssClass="table participants">
					<jsp:attribute name="header">
						<th>name</th>
						<th>task</th>
						<th>email</th>
						<th>amount</th>
						<th>payment method</th>
						<th>actions</th>
					</jsp:attribute>
					<jsp:attribute name="content">
					<c:forEach items="${nonValidatedRegistrations}" var="registration">
							<c:set var="rowspan" value="${registration.additionalParticipants.size() + 1}" />
								<tr class="participant">
									<td>${registration.registrer.participant.name}</td>
									<td>
										<form action="${pageContext.request.contextPath}/direct/admin/event/update/task">
											<input class="participantId" type="hidden" name="participantId" value="${registration.registrer.participant.id}" />
											<input class="amountHtmlId" type="hidden" name="amountHtmlId" value="amount_${registration.id}"/>
											<select class="tasksSelect" name="taskId">
												<c:forEach items="${tasks}" var="task" >
													<c:if test="${registration.registrer.participant.task.id == task.id}">
														<option value="${task.id}"  selected="selected">${task.name}</option>
													</c:if>
													<c:if test="${registration.registrer.participant.task.id != task.id}">
														<option value="${task.id}">${task.name}</option>
													</c:if>
												</c:forEach>
											</select>
										</form>
									</td>
									<td rowspan="${rowspan}">${registration.registrer.email}</td>
									<td rowspan="${rowspan}"><span id="amount_${registration.id}" class="amount">${registration.amount}</span> €</td>
									<td rowspan="${rowspan}">via ${registration.paymentDescription}</td>
									<td rowspan="${rowspan}">
										<form action="${pageContext.request.contextPath}/direct/admin/event/validate/registration">
											<input type="hidden" class="registrationId" name="registrationId" value="${registration.id}" /> <input type="submit"
												value="validate" class="validateButton" />
										</form>
										<br/>
										<form action="${pageContext.request.contextPath}/admin/event/remove/registration">
											<input type="hidden" name="registrationId" value="${registration.id}" /> <input type="submit" value="remove"
												class="removeButton" />
										</form>
									</td>
							</tr>
							
							<c:if test="${!registration.additionalParticipants.isEmpty()}">
								<c:forEach items="${registration.additionalParticipants}" var="participant">
									<tr class="participant">
										<td>${participant.name}</td>
										<td>
										<form action="${pageContext.request.contextPath}/direct/admin/event/update/task">
											<input  class="participantId" type="hidden" name="participantId" value="${participant.id}" />
											<input class="amountHtmlId" type="hidden" name="amountHtmlId" value="amount_${registration.id}"/>
											<select class="tasksSelect" name="taskId">
												<c:forEach items="${tasks}" var="task" >
													<c:if test="${participant.task.id == task.id}">
														<option value="${task.id}"  selected="selected">${task.name}</option>
													</c:if>
													<c:if test="${participant.task.id != task.id}">
														<option value="${task.id}">${task.name}</option>
													</c:if>
												</c:forEach>
											</select>
										</form>
									</td>
									</tr>
								</c:forEach>
							</c:if>
						</c:forEach>
					</jsp:attribute>
				</template:table>
			</c:if>
		</div>
		<div id="actionsHistory">
			<!-- TODO call history controller here instead of ajax call on first page loading -->
		</div>
	</jsp:body>
</template:chapter>
</body>
</html>