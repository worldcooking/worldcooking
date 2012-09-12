<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="user" uri="http://www.mishk.org/tags/security-user" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>users management</h1>

<c:if test="${!groups.isEmpty()}">

	<c:forEach items="${groups}" var="group">
		<template:chapter title="${group.name}">
			
			<c:if test="${!group.isEmpty()}">
			
				<template:table>
					
					<jsp:attribute name="header">
						<th>username</th>
						<th>first name</th>
						<th>last name</th>
						<th>nickname</th>
						<th>email</th>
						<th>phone</th>
						<th>last connection</th>
						<th>actions</th>
					</jsp:attribute>
					
					<jsp:attribute name="content">
						<c:forEach items="${group.users}" var="user">
							<tr>
								<%-- username --%>
								<td><a href="${user.username}">url</a></td>
								<%-- user first name --%>
								<td>${user.firstName}</td>
								<%-- user last name --%>
								<td>${user.lastName}</td>
								<%-- user nickname --%>
								<td>${user.nickname}</td>
								<%-- user email address --%>
								<td>${user.emailAddress}</td>
								<%-- user phone number --%>
								<td>${user.phoneNumber}</td>
								<%-- last connection date --%>
								<td>
									<fmt:formatDate value="${user.lastConnectionDate}" pattern="MM/dd/yyyy HH:mm"/>
								</td>
								<td>
									<c:if test="${group.name == 'GUEST'}">
										<c:url var="makeAdminUrl" value="/admin/security/manage/user/${user.id}/update/group/admin"/>
										<a href="${makeAdminUrl}">promouvoir administrateur</a>
									</c:if> 
									<c:if test="${group.name == 'ADMIN'}">
										<c:url var="makeAdminUrl" value="/admin/security/manage/user/${user.id}/update/group/guest"/>
										<a href="${makeAdminUrl}">rétrograder simple visiteur</a>
										<c:url var="makeAdminUrl" value="/admin/security/manage/user/${user.id}/update/group/super_admin"/>
										<a href="${makeAdminUrl}">promouvoir super-administrateur</a>
									</c:if>
									<c:if test="${group.name == 'SUPER_ADMIN'}">
										<c:url var="makeAdminUrl" value="/admin/security/manage/user/${user.id}/update/group/admin"/>
										<a href="${makeAdminUrl}">rétrograder administrateur</a>
									</c:if> 
								</td>
							</tr>
						</c:forEach>
					</jsp:attribute>
				</template:table>
			</c:if>
			
		</template:chapter>
	</c:forEach>
</c:if>