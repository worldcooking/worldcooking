<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="registrations" required="true" type="java.util.List" %>
<%@ taglib prefix="worldcooking-admin" tagdir="/WEB-INF/tags/worldcooking/admin/event/participants"  %>

<c:if test="${!registrations.isEmpty()}">
	<%-- check if registrations are validated --%>
	<c:if test="${registrations[0].validated}">
		<c:set var="validatedRegistrations" value="true"/>
	</c:if>
	<c:if test="${not registrations[0].validated}">
		<c:set var="validatedRegistrations" value="false"/>
	</c:if>
	
	<%-- display title --%>
	<c:if test="${validatedRegistrations}">
		<h3>List of validated registrations</h3>
	</c:if>
	<c:if test="${not validatedRegistrations}">
		<h3>List of NON validated registrations</h3>
	</c:if>

	<template:table cssClass="table participants">
		<jsp:attribute name="header">
			<th>name</th>
			<th>role</th>
			<th>email</th>
			<th>amount</th>
			<th>payment method</th>
			<th>actions</th>
		</jsp:attribute>
		<jsp:attribute name="content">
			<c:forEach items="${registrations}" var="registration">
				<%-- subscriber --%>
				<c:set var="rowspan" value="${registration.additionalParticipants.size() + 1}" />
				
				<c:set var="participant" value="${registration.registrer}" />
				
				<tr class="participant">
					<%-- subscriber name --%>
					<td>${participant.name}
						<c:if test="${not empty registration.subscriberFullName}">
							<br/>(${registration.subscriberFullName})
						</c:if>
					</td>
					<%-- subscriber role --%>
					<td>
						<worldcooking-admin:registration-participants-role participant="${participant}" eventRoles="${eventRoles}" registrationId="${registration.id}" registrationValidated="${registration.validated}" />
					</td>
					<%-- subscriber email --%>
					<td >${participant.email}</td>
					<%-- amount --%>
					<td rowspan="${rowspan}">
						<span id="amount_${registration.id}" class="amount">${registration.amount}</span> &euro;
					</td>
					<%-- payment method --%>
					<c:if test="${validatedRegistrations}">
						<td rowspan="${rowspan}">payed to ${registration.paymentDescription}</td>
					</c:if>
					<c:if test="${not validatedRegistrations}">
						<td rowspan="${rowspan}">via ${registration.paymentDescription}</td>
					</c:if>
					<%-- actions --%>
					<td rowspan="${rowspan}">
						<c:if test="${validatedRegistrations}">
							<%-- validated registration: actions is UNVALIDATE --%>
							<c:url var="unvalidateRegistrationUrl" value="/direct/admin/event/${event.reference}/unvalidate/registration" />
							<form action="${unvalidateRegistrationUrl}">
								<input type="hidden" class="registrationId" name="registrationId" value="${registration.id}" />
								<input type="submit" value="unvalidate" class="unvalidateButton" />
							</form>
						</c:if>
						<c:if test="${not validatedRegistrations}">
							<%--not validated registration: actions are VALIDATE and REMOVE --%>
							<c:url var="validateRegistrationUrl" value="/direct/admin/event/${event.reference}/validate/registration" />
							<form action="${validateRegistrationUrl}">
								<input type="hidden" class="registrationId" name="registrationId" value="${registration.id}" />
								<input type="submit" value="validate" class="validateButton" />
							</form>
							<br />
							<c:url var="removeRegistrationUrl" value="/admin/event/${event.reference}/remove/registration" />
							<form action="${removeRegistrationUrl}">
								<input type="hidden" class="registrationId" name="registrationId" value="${registration.id}" />
								<input type="submit" value="remove" class="removeButton" />
							</form>
						</c:if>
					</td>
				</tr>
				
				<c:if test="${!registration.additionalParticipants.isEmpty()}">
					<%-- additional participants --%>
					<c:forEach items="${registration.additionalParticipants}" var="participant">
						<tr class="participant">
							<%-- additional participant name --%>
							<td>${participant.name}</td>
							<%-- additional participant role --%>
							<td>
								<worldcooking-admin:registration-participants-role participant="${participant}" eventRoles="${eventRoles}" registrationId="${registration.id}" registrationValidated="${registration.validated}" />
							</td>
							<%-- additional participant email --%>
							<td >${participant.email}</td>
						</tr>
					</c:forEach>
				</c:if>
			</c:forEach>
		</jsp:attribute>
	</template:table>
</c:if>