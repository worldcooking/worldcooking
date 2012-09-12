<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="resources" uri="http://www.mishk.org/tags/resources"%>
<%@ taglib prefix="jquery" uri="http://www.mishk.org/tags/jquery"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="user" uri="http://www.mishk.org/tags/security-user" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="template-form" uri="http://www.mishk.org/tags/template-form"  %>

<h1>Espace réservé aux administrateurs du site</h1>

<jquery:require-static-resources />
<resources:require-css resourceURI="authentication/login.css" locationType="JAR"/>
<resources:require-script resourceURI="authentication/login.js" locationType="JAR"/>

	<p>Sélectionnez votre fournisseur OpenID: </p>
	<form action="j_spring_openid_security_check" id="googleOpenIdForm" class="openIdForm"
			method="post" target="_top">
		<!-- google -->
		<input id="openid_identifier" name="openid_identifier" type="hidden"
			value="https://www.google.com/accounts/o8/id" /> 
		<img
			id="googleOpenIdLink" class="openIdLink"
			src="https://ssl.gstatic.com/images/logos/google_logo_41.png" />
		<!-- yahoo -->
	</form>
	<form action="j_spring_openid_security_check" id="yahooOpenIdForm" class="openIdForm"
			method="post" target="_top">
	
		<input id="openid_identifier" name="openid_identifier" type="hidden"
			value="http://yahoo.com" /> 
		<c:set var="yahooLogoUrl">
			<c:url value="/resources/img/yahoo-logo.png"/>
		</c:set>
		
		<img
			id="yahooOpenIdLink" class="openIdLink" src="${yahooLogoUrl}" />
	</form>
	
	<form name='oidf' action='j_spring_openid_security_check'
		method='POST'>
		<p>
			Ou entrez manuellement son URL: <input type='text'
				name='openid_identifier' /> <input name="submit" type="submit" />
		</p>
	</form>
