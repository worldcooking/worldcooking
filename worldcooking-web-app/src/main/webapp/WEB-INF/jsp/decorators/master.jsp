<%@page import="org.worldcooking.web.authentication.MishkUserHolder"%>
<%@ page  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="res" uri="http://www.oups-asso.org/mish-k/tags/resources" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="web-security" uri="http://www.oups-asso.org/mish-k/tags/web-security" %>
<decorator:usePage id="myPage" />
<html>
<head>
<title><decorator:title default="Worldcooking" /></title>

<decorator:head/>

<res:import-required-css 
	internalResourcesPrefixPath="css" 
	externalResourcesPrefixPath="resources/css" />

<res:import-required-scripts 
	internalResourcesPrefixPath="js" 
	externalResourcesPrefixPath="resources/js" />

</head>
<body>

<web-security:login-area />

	<div class="header">
		<div class="header_title">
			<h1><decorator:title /></h1>
		</div>
	</div>

	<div id="main" class="main">
		<decorator:body />
	</div>
	<div class="info_bottom">
		If you have problems with this page, please contact <a href="mailto:matthieutrashbox@gmail.com">matthieutrashbox@gmail.com</a>
	</div>
</body>
</html>
