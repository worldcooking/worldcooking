<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.mishk.org/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.mishk.org/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.mishk.org/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"  %>
<%@ taglib prefix="tabs" uri="http://www.mishk.org/tags/template-tabs"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Shop administration</title>
	<jquery:require-static-resources />
	<jquery-ui:require-static-resources />
	<resources:require-script resourceURI="site/admin/event/site-admin-shop.js" />
</head>
<body>

<c:url var="adminShopCatalogsUrl" value="/direct/admin/shop/catalogs" />
<c:url var="adminShopStocksUrl" value="/direct/admin/shop/stocks" />
<c:url var="adminShoopingBagsUrl" value="/direct/admin/shop/shopping-bags" />
							
<tabs:menu>
	<tabs:entry title="Catalogs" ajaxUrl="${adminShopCatalogsUrl}" />
	<tabs:entry title="Stocks" ajaxUrl="${adminShopStocksUrl}" />
	<tabs:entry title="Shopping bags" ajaxUrl="${adminShoopingBagsUrl}" />
</tabs:menu>

</body>
</html>