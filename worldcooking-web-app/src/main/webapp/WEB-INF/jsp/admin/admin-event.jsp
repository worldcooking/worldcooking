<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>

<html>
<head>
<title>Administration</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administration.css" media="screen"
	title="bbxcss" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin-event.css"
	media="screen" title="bbxcss" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/3rdparty/jquery-1.7.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/3rdparty/jquery-ui-1.8.16/ui/minified/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/worldcooking/history/history.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/worldcooking/admin/admin-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/worldcooking/admin/admin-event_update-task.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/worldcooking/admin/admin-event_validate.js"></script>

</head>
<body>

<!-- AJAX URLS -->
<input  id="updateTaskAjaxUrl" type="hidden" name="updateTaskUrl" value="${pageContext.request.contextPath}/direct/admin/event/update/task" />


<!-- TODO dynamic auto update from server push (and add update to history: 'List of participants updated by Peter at 8:30 [open a chat with him]') -->

	<div class="main_chapter">
		<h2>
			Administration of '${event.name}' event
		</h2>

		<div id="validated_registrations">
			<c:if test="${!validatedRegistrations.isEmpty()}">
				<h3>List of validated registrations</h3>
				<table class="table">
					<thead>
						<tr>
							<th>name</th>
							<th>task</th>
							<th>email</th>
							<th>amount</th>
							<th>payment method</th>
							<th>actions</th>
						</tr>
					</thead>
					<tbody>
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
					</tbody>
				</table>
			</c:if>
		</div>

		<div id="unvalidated_registrations">
			<c:if test="${!nonValidatedRegistrations.isEmpty()}">
				<h3>List of NON validated registrations</h3>
				<table class="table participants">
					<thead>
						<tr>
							<th>name</th>
							<th>task</th>
							<th>email</th>
							<th>amount</th>
							<th>payment method</th>
							<th>actions</th>
						</tr>
					</thead>
					<tbody>
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
					</tbody>
				</table>
			</c:if>
		</div>
		<div id="actionsHistory">
			<!-- TODO call history controller here instead of ajax call on first page loading -->
		</div>
	</div>
</body>
</html>