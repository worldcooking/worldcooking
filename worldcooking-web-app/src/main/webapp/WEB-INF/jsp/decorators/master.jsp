<%@ page  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="res" uri="http://www.oups-asso.org/mish-k/tags/resources" %>
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
<body onload="<decorator:getProperty property="body.onload"/>">
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
