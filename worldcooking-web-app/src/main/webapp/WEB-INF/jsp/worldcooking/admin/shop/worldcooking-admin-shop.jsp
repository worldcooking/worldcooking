<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="tabs" uri="http://www.oups-asso.org/mish-k/tags/template-tabs"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Shop administration</title>
	<jquery:require-static-resources />
	<jquery-ui:require-static-resources />
	<resources:require-script resourceURI="worldcooking/admin/event/worldcooking-admin-shop.js" />
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