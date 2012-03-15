<%@ taglib prefix="web-security" uri="http://www.mishk.org/tags/security" %>
<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><html>
<head>
	<title>Worldcooking - administration</title>
</head>
<body>

<div class="usersManagementContainer">

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
										<c:if test="${group.name == 'SUPER_ADMIN'}">
											<c:url var="makeAdminUrl" value="/admin/security/manage/user/${user.id}/update/group/admin"/>
											<a href="${makeAdminUrl}">ungrant</a>
										</c:if>
										<c:if test="${group.name == 'ADMIN'}">
											<c:url var="makeAdminUrl" value="/admin/security/manage/user/${user.id}/update/group/super_admin"/>
											<a href="${makeAdminUrl}">grant</a> |
											<c:url var="makeAdminUrl" value="/admin/security/manage/user/${user.id}/update/group/user"/>
											<a href="${makeAdminUrl}">ungrant</a>
										</c:if>
										<c:if test="${group.name != 'SUPER_ADMIN' && group.name != 'ADMIN'}">
											<c:url var="makeAdminUrl" value="/admin/security/manage/user/${user.id}/update/group/admin"/>
											<a href="${makeAdminUrl}">grant</a>
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

</div>
</body>
</html>