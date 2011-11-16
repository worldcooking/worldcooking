<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Administration</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administration.css" media="screen" title="bbxcss" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin/admin-event.css" media="screen" title="bbxcss" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/3rdparty/jquery-1.7.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/admin-event.js"></script> 
</head>
<body>
	<div class="main_chapter">
		<h2>Administration of '<c:out value="${event.name}"/>' event</h2>
		
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
							<c:set var="rowspan" value="${registration.additionalParticipants.size() + 1}"/>
							<tr>
								<td>${registration.registrer.participant.name}</td>
								<td>${registration.registrer.participant.task.name}</td>
								<td rowspan="${rowspan}">${registration.registrer.email}</td>
								<td rowspan="${rowspan}">${registration.amount} €</td>
								<td rowspan="${rowspan}">payed to ${registration.paymentDescription}</td>
								<td rowspan="${rowspan}">
									<form action="${pageContext.request.contextPath}/admin/event/unvalidate/registration">
										<input type="hidden" name="registrationId" value="${registration.id}" />
										<input type="submit" value="unvalidate" class="unvalidateButton" />
									</form>
								</td>
							</tr>
							<c:if test="${!registration.additionalParticipants.isEmpty()}">
								<c:forEach items="${registration.additionalParticipants}" var="participant">
									<tr>
										<td>${participant.name}</td>
										<td>${participant.task.name}</td>
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
						<c:forEach items="${nonValidatedRegistrations}" var="registration">
							<c:set var="rowspan" value="${registration.additionalParticipants.size() + 1}"/>
							<tr>
								<td>${registration.registrer.participant.name}</td>
								<td>${registration.registrer.participant.task.name} 
									<form:form commandName="taskForm" action="${pageContext.request.contextPath}/admin/event/update/task" >
										<input type="hidden" name="participantId" value="${registration.registrer.participant.id}" />
										<form:select cssClass="tasksSelect" path="taskId" items="${tasks}"  multiple="false"  />
										<input type="submit" value="update" class="changeTaskButton" />
									</form:form>
								</td>
								<td rowspan="${rowspan}">${registration.registrer.email}</td>
								<td rowspan="${rowspan}">${registration.amount} €</td>
								<td rowspan="${rowspan}">via ${registration.paymentDescription}</td>
								<td rowspan="${rowspan}">
									<form action="${pageContext.request.contextPath}/admin/event/validate/registration">
										<input type="hidden" name="registrationId" value="${registration.id}" />
										<input type="submit" value="validate" class="validateButton" />
									</form>
									<form action="${pageContext.request.contextPath}/admin/event/remove/registration">
										<input type="hidden" name="registrationId" value="${registration.id}" />
										<input type="submit" value="remove" class="removeButton" />
									</form>
								</td>
							</tr>
							<c:if test="${!registration.additionalParticipants.isEmpty()}">
								<c:forEach items="${registration.additionalParticipants}" var="participant">
									<tr>
										<td>${participant.name}</td>
										<td>${participant.task.name}</td>
									</tr>
								</c:forEach>	
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
		<div id="actionsHistory">
			<h4>Actions history</h4>
			<div>You have update task X to Y for participant Z [<a href="#">undo</a>]</div>
		</div>
	</div>
</body>
</html>