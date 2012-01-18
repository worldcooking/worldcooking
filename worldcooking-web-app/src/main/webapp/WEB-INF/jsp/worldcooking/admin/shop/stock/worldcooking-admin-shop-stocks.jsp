<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.oups-asso.org/mish-k/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.oups-asso.org/mish-k/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.oups-asso.org/mish-k/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.oups-asso.org/mish-k/tags/template"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="stock" items="${stocks}">
	<template:chapter title="${stock.name}" >
		<u>Stock reference</u>: ${stock.reference}
		
		<ul>
		<c:forEach var="stockProduct" items="${stock.stockProducts}">
			<li><b>${stockProduct.product.name}</b> (${stockProduct.quantity} - ${stockProduct.product.price}€) :  ${stockProduct.product.description}</li>
		</c:forEach>
		</ul>

	</template:chapter>
</c:forEach>
