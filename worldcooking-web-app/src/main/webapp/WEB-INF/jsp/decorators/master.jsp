<%@page import="org.oupsasso.mishk.web.security.authentication.MishkUserHolder"%>
<%@ page  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="web-security" uri="http://www.oups-asso.org/mish-k/tags/web-security" %>
<%@ taglib prefix="template"
	uri="http://www.oups-asso.org/mish-k/tags/template"%>
<decorator:usePage id="myPage" />
<html>
<head>
<title><decorator:title default="Worldcooking" /></title>

<%-- ensure that template CSS are loaded first (cause it is customized by worldcooking CSS files) --%>
<template:require-static-resources />
	
<decorator:head/>

<web-security:require-static-resources />

<resources:import-required-css 
	internalResourcesPrefixPath="css" 
	externalResourcesPrefixPath="resources/css" />

<resources:import-required-scripts 
	internalResourcesPrefixPath="js" 
	externalResourcesPrefixPath="resources/js" />

</head>
<body>

	<template:layout>
		<jsp:attribute name="headerCenter">
			<div id="header_title"><h1><decorator:title /></h1></div>
		</jsp:attribute>
		
		<jsp:attribute name="bodyRight">
			<web-security:login-area />
		</jsp:attribute>
		
		
		<jsp:attribute name="footerCenter">
			If you have problems with this page, please contact <a href="mailto:matthieutrashbox@gmail.com">matthieutrashbox@gmail.com</a>
		</jsp:attribute>
		
		<jsp:body>
		    <decorator:body />
	    </jsp:body>
	</template:layout>
	
</body>
</html>
