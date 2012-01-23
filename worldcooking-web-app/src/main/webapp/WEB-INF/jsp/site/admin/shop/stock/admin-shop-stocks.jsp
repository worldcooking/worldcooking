<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="resources" uri="http://www.mishk.org/tags/resources"  %>
<%@ taglib prefix="jquery" uri="http://www.mishk.org/tags/jquery" %>
<%@ taglib prefix="jquery-ui" uri="http://www.mishk.org/tags/jquery-ui" %>
<%@ taglib prefix="template" uri="http://www.mishk.org/tags/template"  %>
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

