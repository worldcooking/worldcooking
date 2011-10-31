<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<decorator:usePage id="myPage" />
<html>
<head>
<title><decorator:title default="Worldcooking" /></title>
<link rel="stylesheet" type="text/css" href="css/main.css"
	media="screen" title="bbxcss" />
<style type="text/css">
</style>
<script type="text/javascript" src="js/main.js">
		</script> 
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
</body>
</html>
