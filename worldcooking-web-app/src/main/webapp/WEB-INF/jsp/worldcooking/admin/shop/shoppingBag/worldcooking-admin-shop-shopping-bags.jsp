<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<template:chapter  >

<c:forEach var="shoppingBag" items="${shoppingBags}">
	<template:chapter title="${shoppingBag.shopping.reference}" >
		<ul>
		<c:forEach var="sBagProduct" items="${shoppingBag.shoppingBagProducts}">
			<li><b>${sBagProduct.product.name}</b> (${sBagProduct.quantity} - ${sBagProduct.unitPrice}€ - ${sBagProduct.product.price}€) :  ${stockProduct.product.description}</li>
		</c:forEach>
		</ul>

	</template:chapter>
</c:forEach>


</template:chapter>
