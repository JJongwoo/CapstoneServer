<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!--위에 뜨는 메뉴-->
<header>
	<nav class="navbar navbar-expand-md navbar-light fixed-top bg-warning">
		<a id="menuimg" class="navbar-brand" href="<c:url value="/"/>"> <img
			src="<c:url value="resources/image/menuimg.png"/>" alt="Logo"
			style="width: 60px;" />
		</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="<c:url value="/"/>" style="color: black;"><strong>홈</strong><span
						class="sr-only">(current)</span></a></li>

				<c:if test="${pageContext.request.userPrincipal.name!=null }">
					<c:if test="${pageContext.request.userPrincipal.name != 'admin' }">
					<li class="nav-item dropdown">
					<a	class="nav-link dropdown-toggle" href="#" id="navbardrop"
						data-toggle="dropdown" style="color:black;"><strong> 활동</strong> </a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="<c:url value="/activity/${pageContext.request.userPrincipal.name}"/>"> 실시간 활동 </a>
							<a class="dropdown-item" href="<c:url value="/emergency/${pageContext.request.userPrincipal.name}"/>"> 긴급 상황  </a>
						</div>
					</li>
					</c:if>
					<c:if test="${pageContext.request.userPrincipal.name == 'admin' }">
						<li class="nav-item"><a class="nav-link"
							href="<c:url value="/admin"/>" style="color: black;">관리자 페이지</a></li>
					</c:if>

					<%-- 이 메뉴를 선택하면 자동으로 logout이라는 id를 가진 form을 submit함 --%>
					<li class="nav-item"><a class="nav-link"
						href="javascript:document.getElementById('logout').submit()"
						style="color: black;"><strong>로그아웃</strong></a></li>

					<form id="logout" action="<c:url value="/logout"/>" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>

				</c:if>



				<c:if test="${pageContext.request.userPrincipal.name==null }">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/login"/>" style="color: black;"><strong>로그인</strong></a></li>
				</c:if>

			</ul>
			<!-- <form class="form-inline mt-2 mt-md-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form> -->
		</div>
	</nav>
</header>