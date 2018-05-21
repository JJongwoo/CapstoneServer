<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h2>응급 상황 조회</h2>
		<br/>
		<div class="dropdown">
				<button class="btn btn-dark dropdown-toggle" type="button"
					data-toggle="dropdown">
					상황별 조회 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="<c:url value="/emergency/slip/${pageContext.request.userPrincipal.name}"/>">낙상 조회</a></li>
					<li><a class="dropdown-item" href="<c:url value="/emergency/sos/${pageContext.request.userPrincipal.name}"/>">SOS 조회</a></li>
					<li><a class="dropdown-item" href="<c:url value="/emergency/theft/${pageContext.request.userPrincipal.name}"/>">외부 침입 조회</a></li>
				</ul>
		</div>
		<br/>
		<br/>
		<div class="table-responsive">
			<h3>가장 최근에 감지된 응급상황 <a href="#" onclick="history.go(0)"><i class="fa fa-refresh fa-pull-right" style="color:black;"></i></a></h3>
			<table class="table table-hover" style="text-align: center;">
				<tr>
					<td>낙상 감지 시각</td>
					<td>${sliptime}</td>
				</tr>
				<tr>
					<td>SOS요청 시각</td>
					<td>${sostime}</td>
				</tr>
				<tr>
					<td>외부침입 감지 시각</td>
					<td>${thefttime}</td>
				</tr>
			</table>
		</div>
	</div>
</div>