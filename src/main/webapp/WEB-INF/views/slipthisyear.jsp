<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container-wrapper">
	<div class="container">
			<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					data-toggle="dropdown">
					상황별 조회 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item"
						href="<c:url value="/emergency/slip/${pageContext.request.userPrincipal.name}"/>">낙상
							조회</a></li>
					<li><a class="dropdown-item"
						href="<c:url value="/emergency/sos/${pageContext.request.userPrincipal.name}"/>">SOS
							조회</a></li>
					<li><a class="dropdown-item"
						href="<c:url value="/emergency/theft/${pageContext.request.userPrincipal.name}"/>">외부
							침입 조회</a></li>
				</ul>
			</div>
			<br/>
			<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					data-toggle="dropdown">
					기간별 조회 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item"
						href="<c:url value="/emergency/slip/thismonth/${pageContext.request.userPrincipal.name}"/>">${month}월
							조회</a></li>
					<li><a class="dropdown-item"
						href="<c:url value="/emergency/slip/lastmonth/${pageContext.request.userPrincipal.name}"/>">${lastmonth}월
							조회</a></li>
					<li><a class="dropdown-item"
						href="<c:url value="/emergency/slip/thisyear/${pageContext.request.userPrincipal.name}"/>">이번연도
							조회</a></li>
				</ul>
			</div>
		<br />

		<div class="table-responsive">
			<h2>${name}님의
				낙상사고 조회<a href="javascript:history.back()"><i
					class="fa fa-arrow-circle-left fa-pull-right"
					style="color: black; font-size: 20px;">뒤로가기</i></a> <a href="#"
					onclick="history.go(0)"><i class="fa fa-refresh fa-pull-right"
					style="color: black; font-size: 20px;">새로고침</i></a>
			</h2>
			<h4>(이번연도기록이 조회됩니다.)</h4>
			<table class="table table-hover" style="text-align: center;">
				<thead class="thead-dark">
					<tr>
						<th>시간</th>
						<th>낙상 사고</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="slip" items="${slip}">
						<tr>
							<td>${slip.datetime}</td>
							<td>낙상</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>