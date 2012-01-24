<%@page
	import="org.mishk.security.authentication.MishkUserHolder"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="resources"
	uri="http://www.mishk.org/tags/resources"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="web-security"
	uri="http://www.mishk.org/tags/security"%>
<%@ taglib prefix="template"
	uri="http://www.mishk.org/tags/template"%>
<decorator:usePage id="myPage" />
<html>
<head>
<title><decorator:title default="Worldcooking" /></title>

<%-- ensure that template CSS are loaded first (cause it is overridden by Worldcooking CSS files) --%>
<template:require-static-resources theme="base" jqueryUiTheme="redmond" />

<decorator:head />

<web-security:require-static-resources />

<resources:require-css resourceURI="site/layout.css" locationType="INTERNAL" />

<resources:import-required-css internalResourcesPrefixPath="css"
	externalResourcesPrefixPath="resources/css" />

<resources:import-required-scripts internalResourcesPrefixPath="js"
	externalResourcesPrefixPath="resources/js" />

</head>
<body>

	<template:layout>

		<jsp:attribute name="headerLeft">
			<jsp:include page="/WEB-INF/jsp/layout/header/headerLeft.jsp" />
		</jsp:attribute>

		<jsp:attribute name="headerCenter">
		<div id="header_title">
				<h1>
					<decorator:title />
				</h1>
			</div>
<%-- 			<jsp:include page="/WEB-INF/jsp/layout/header/headerCenter.jsp"/>
 --%>		</jsp:attribute>
		<jsp:attribute name="headerRight">
			<jsp:include page="/WEB-INF/jsp/layout/header/headerRight.jsp" />
		</jsp:attribute>

		<jsp:attribute name="bodyLeft">
			<jsp:include page="/WEB-INF/jsp/layout/body/bodyLeft.jsp" />
		</jsp:attribute>

		<jsp:attribute name="bodyRight">
			<jsp:include page="/WEB-INF/jsp/layout/body/bodyRight.jsp" />
		</jsp:attribute>

		<jsp:attribute name="footerLeft">
			<jsp:include page="/WEB-INF/jsp/layout/footer/footerLeft.jsp" />
		</jsp:attribute>

		<jsp:attribute name="footerCenter">
			<jsp:include page="/WEB-INF/jsp/layout/footer/footerCenter.jsp" />
		</jsp:attribute>

		<jsp:attribute name="footerRight">
			<jsp:include page="/WEB-INF/jsp/layout/footer/footerRight.jsp" />
		</jsp:attribute>

		<jsp:body>
			<decorator:body />
	    </jsp:body>
	</template:layout>

</body>
</html>
