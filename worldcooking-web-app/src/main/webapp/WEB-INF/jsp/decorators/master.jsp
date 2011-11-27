<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<decorator:usePage id="myPage" />
<html>
<head>
<title><decorator:title default="Worldcooking" /></title>
<decorator:head/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"
	media="screen" title="bbxcss" />
<style type="text/css">
</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/worldcooking/main.js"></script> 
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
