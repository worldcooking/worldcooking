<%@page
	import="org.oupsasso.mishk.security.authentication.MishkUserHolder"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="resources"
	uri="http://www.oups-asso.org/mish-k/tags/resources"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="web-security"
	uri="http://www.oups-asso.org/mish-k/tags/web-security"%>
<%@ taglib prefix="template"
	uri="http://www.oups-asso.org/mish-k/tags/template"%>
<decorator:usePage id="myPage" />
<html>
<head>
<title><decorator:title default="Worldcooking" /></title>

<%-- ensure that template CSS are loaded first (cause it is overridden by Worldcooking CSS files) --%>
<template:require-static-resources theme="dynamic-colors" />

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
			<jsp:include page="../layout/header/headerLeft.jsp" />
		</jsp:attribute>

		<jsp:attribute name="headerCenter">
		<div id="header_title">
				<h1>
					<decorator:title />
				</h1>
			</div>
<%-- 			<jsp:include page="../layout/header/headerCenter.jsp"/>
 --%>		</jsp:attribute>
		<jsp:attribute name="headerRight">
			<jsp:include page="../layout/header/headerRight.jsp" />
		</jsp:attribute>

		<jsp:attribute name="bodyLeft">
			<jsp:include page="../layout/body/bodyLeft.jsp" />
		</jsp:attribute>

		<jsp:attribute name="bodyRight">
			<jsp:include page="../layout/body/bodyRight.jsp" />
		</jsp:attribute>

		<jsp:attribute name="footerLeft">
			<jsp:include page="../layout/footer/footerLeft.jsp" />
		</jsp:attribute>

		<jsp:attribute name="footerCenter">
			<jsp:include page="../layout/footer/footerCenter.jsp" />
		</jsp:attribute>

		<jsp:attribute name="footerRight">
			<jsp:include page="../layout/footer/footerRight.jsp" />
		</jsp:attribute>

		<jsp:body>
			<decorator:body />
	    </jsp:body>
	</template:layout>

</body>
</html>
