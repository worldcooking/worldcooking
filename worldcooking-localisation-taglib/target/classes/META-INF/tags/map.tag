<%@ attribute name="mapContainerId" required="true" rtexprvalue="false"%>
<%@ attribute name="name" required="true" rtexprvalue="false"%>
<%@ attribute name="addressLine1" required="false" rtexprvalue="false"%>
<%@ attribute name="addressLine2" required="false" rtexprvalue="false"%>
<%@ attribute name="addressLine3" required="false" rtexprvalue="false"%>
<%@ attribute name="mapLinkText" required="false" rtexprvalue="false"%>
<%@ attribute name="flag" required="false" rtexprvalue="false"%>
<%@ attribute name="zoom" required="false" rtexprvalue="false"   %>
<%@ attribute name="latitude" required="false" rtexprvalue="false"   %>
<%@ attribute name="longitude" required="false" rtexprvalue="false"   %>
<%@ attribute name="width" required="false" rtexprvalue="false"   %>
<%@ attribute name="height" required="false" rtexprvalue="false"   %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="${mapContainerId}Parent" class="address" >
	<c:if test="${not empty name}">
		<b>${name}</b>
		<c:set var="fullAddress" value="${name}"/>
	</c:if>
	<c:if test="${not empty addressLine1}">
		<br />${addressLine1}
		<c:set var="fullAddress" value="${fullAddress}, ${addressLine1}"/>
	</c:if>
	<c:if test="${not empty addressLine1}">
		<br />${addressLine2}
		<c:set var="fullAddress" value="${fullAddress}, ${addressLine2}"/>
	</c:if>
	<c:if test="${not empty addressLine1}">
		<br />${addressLine3}
		<c:set var="fullAddress" value="${fullAddress}, ${addressLine3}"/>
	</c:if>
	<c:if test="${empty mapLinkText}">
		<c:set var="mapLinkText" value="See the map"/>
	</c:if>
	<c:if test="${empty zoom}">
		<c:set var="zoom" value="13"/>
	</c:if>
	<c:if test="${empty width}">
		<c:set var="width" value="100%"/>
	</c:if>
	<c:if test="${empty height}">
		<c:set var="height" value="100%"/>
	</c:if>
	<br />
	<c:url var="mapUrl" value="http://maps.google.fr/maps" >
		<c:param name="q">${fullAddress}</c:param>
		<c:param name="iwloc">A</c:param>
	</c:url>
	<!-- link to the map -->
	<a href="${mapUrl}" target="_blank">${mapLinkText}</a>
	<div id="${mapContainerId}" class="map" style="width:${width}; height:${height}; display:none">
	</div>
	<c:if test="${empty latitude or empty longitude}">
		<script type="text/javascript">
		$(document).ready(function() {
		   localizeWithGoogleMap("${fullAddress}");
		});
		</script>
	</c:if>
	
	<c:if test="${not empty latitude and not empty longitude}">
		<script type="text/javascript">
		var map;
		$(document).ready(function() {
		  
		  var mapContainer = document.getElementById('${mapContainerId}');
		  
			var mapContainerParent = $("#${mapContainerId}Parent");
			mapContainerParent.bind("mouseover", function(){
			  // show map on mouse over
			  $(mapContainer).show();
			
		      if (map == null){
		    	// load map
				map = displayGoogleMap(mapContainer,'${name}', '${fullAddress}', <c:out value="${latitude}" />, <c:out value="${longitude}" />, <c:out value="${zoom}" />, '${flag}');
		      }
			  
			});
			mapContainerParent.bind("mouseout", function(){
			  // hide map on mouse out
			  $(mapContainer).hide();
			});
		});
		</script>
	</c:if>
</div>