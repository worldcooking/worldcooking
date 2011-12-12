<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- AJAX URLS -->
<input  id="showHistoryAjaxUrl" type="hidden" name="showHistoryAjaxUrl" value="${pageContext.request.contextPath}/direct/history" />

<c:if test="${not empty history.historyEntries}">
	<h3>Last actions</h3>
	<c:forEach items="${history.historyEntries}" var="historyEntry" end="2" varStatus="loopStatus">
		<b><fmt:formatDate pattern="HH:mm" value="${historyEntry.date}"/></b>: 
		<c:forEach items="${historyEntry.messageFragments}" var="messageFragment">
			<c:if test="${messageFragment.fragmentType == 'IMPORTANT'}">
				<span class="important">${messageFragment.message}</span>
			</c:if>
			<c:if test="${messageFragment.fragmentType != 'IMPORTANT'}">
				${messageFragment.message}
			</c:if>
		</c:forEach>
		<c:if test="${loopStatus.isFirst()}">
			[<a href="${historyEntry.undoLink}">undo</a>]
		</c:if>
		<br/>	
	</c:forEach>
</c:if>