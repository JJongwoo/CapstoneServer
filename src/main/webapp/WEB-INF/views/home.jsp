<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
	<div class="col-md-8 p-lg-6 mx-auto my-8">
	
		<img src="<c:url value="resources/image/helloGrandMa.png"/>"
			class="img-responsive"
			style="display: block; max-width: 60%; height: auto; max-height: 60%; margin: auto;">
		<br/>
		<p class="lead font-weight-normal">독거노인의 안전한 노후 생활을 위한 프로젝트</p>
		</br/>
		<img src="<c:url value="resources/image/main_visual1.jpg"/>"
			class="img-responsive"
			style="display: block; max-width: 100%; height: auto; max-height: 100%; margin: auto;">
	</div>
	<!-- <div class="product-device box-shadow d-none d-md-block"></div>
	<div class="product-device product-device-2 box-shadow d-none d-md-block"></div> -->
</div>

<div class="d-md-flex flex-md-equal w-100 my-md-3 pl-md-3">
	<div
		class="bg-dark mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
		<div class="my-3 py-3">
			<h2>Hello GrandMa 시스템은?</h2>
			<p>
				동작 및 환경 감지센서, SOS버튼 등을 활용해</br> 독거노인이 잘 지내고 계신지 확인하고</br> 응급상황시 알림을 제공하는
				서비스입니다.
			</p>
		</div>
		<div class="bg-light box-shadow mx-auto"
			style="width: 85%; height: 70%; border-radius: 21px 21px 0 0;">
			<img src="<c:url value="resources/image/main_visual2.png"/>"
				class="img-responsive"
				style="display: block; max-width: 100%; height: auto; max-height: 100%; margin: auto;">
		</div>
	</div>
	<div
		class="bg-light mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
		<div class="my-3 p-3">
			<h2 class="display-5">엄마 요즘 잘 지내지?</h2>
			<p class="lead">24시간 부모님을 지켜드리는 안심 지킴이</p>
		</div>
		<img class="rounded-circle"
			src="<c:url value="resources/image/main_visual3.jpg"/>"
			alt="Second slide" class="img-responsive"
			style="display: block; max-width: 90%; height: auto; max-height: 100%; margin: auto;">
		<!-- <div class="bg-dark box-shadow mx-auto"
			style="width: 80%; height: 300px; border-radius: 21px 21px 0 0;">
			
		</div> -->

	</div>
</div>



<!-- Marketing messaging and featurettes
      ================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->
</br>
</br>
<c:if test="${pageContext.request.userPrincipal.name!=null }">
	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row" style="text-align: center;">
			<div class="col-lg-6">
				<img class="rounded-circle"
					src="<c:url value="resources/image/activity.png"/>"
					 width="170" height="170">
				<h2>실시간 활동</h2>
				<p>현재 상태와 오늘 활동 내역을 볼 수 있습니다.</p>
				<p>
					<a class="btn btn-secondary" href="<c:url value="/activity/${pageContext.request.userPrincipal.name}"/>" role="button">실시간 활동 확인 &raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-6">
				<img class="rounded-circle"
					src="<c:url value="resources/image/emergency.png"/>"
					 width="170" height="170">
				<h2>응급 상황</h2>
				<p>오늘 현재 시각까지의 응급 상황을 조회 할수 있습니다.</p>
				<p>
					<a class="btn btn-secondary" href="<c:url value="/emergency/${pageContext.request.userPrincipal.name}"/>" role="button">응급 상황 내역 확인 &raquo;</a>
				</p>
			</div>
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
</c:if>