<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title><tiles:insertAttribute name="title" /></title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="resources/css/bootstrap.min.css"/>"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="resources/css/carousel.css"/>"
	rel="stylesheet">
<link href="<c:url value="resources/css/main.css"/>"
	rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<c:url value="resources/css/product.css"/>" rel="stylesheet">
<!-- Custom styles for this template -->
 <link href="<c:url value="resources/css/product.css"/>" rel="stylesheet">
    
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>     
<%-- <script src="https://code.jquery.com/jquery-3.2.1.js"></script> 
<link rel="stylesheet" href="<c:url value="resources/css/jquery-ui.min.css"/>"/><!-- 
<script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>   -->  --%>

    
    
    
<!-- <script language='javascript'> 
window.setTimeout('window.location.reload()',4000);
</script> -->


</head>
<body>

	<div>
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>

	<!--자바 스크립트를 쓰려면 아래 세줄이 꼭 들어가야함-->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script src="<c:url value="resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="resources/js/jquery.min.js"/>"></script>
</body>
</html>
