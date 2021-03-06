<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Online Shopp Website Using Spring MVC and Hibernate">
<meta name="author" content="Fiodor Mitcov">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>Online Shop - ${title}</title>

<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Spacelab Theme-->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- DataTable-->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./layout/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">

			<c:if test="${userClickHome == true }">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- User clicks about -->
			<c:if test="${userClickAbout == true }">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- User clicks contact -->
			<c:if test="${userClickContact == true }">
				<%@include file="contact.jsp"%>
			</c:if>

			<!-- User clicks  AllProduct or Category -->
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@include file="listproduct.jsp"%>
			</c:if>

			<!-- User clicks single product -->
			<c:if test="${userClickShowProduct == true}">
				<%@include file="singleproduct.jsp"%>
			</c:if>
			
			<!-- Clicks to manage product -->
			<c:if test="${userClickManageProducts == true}">
				<%@include file="manageproducts.jsp"%>
			</c:if>
			
			<!-- Clicks to manage product -->
			<c:if test="${userClickShowCart == true}">
				<%@include file="cart.jsp"%>
			</c:if>


		</div>

		<!-- Footer -->
		<%@include file="./layout/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		<script src="${js}/jquery.validate.min.js"></script>
		<script src="${js}/popper.min.js"></script>
		<script src="${js}/bootstrap.min.js"></script>
		<script src="${js}/jquery.dataTables.js"></script>
		<%-- 		<script src="${js}/dataTables.bootstrap.js"></script> --%>
		<script src="${js}/bootbox.min.js"></script>
		<!-- My own JavaScript -->
		<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>


