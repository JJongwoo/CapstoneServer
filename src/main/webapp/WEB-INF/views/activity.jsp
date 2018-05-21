<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="container-wrapper">

		<h3>현재 상태  <a href="#" onclick="history.go(0)"><i class="fa fa-refresh fa-pull-right" style="color:black;"></i></a> </h3>
		<br/>
		<div class="table-responsive">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th>현재 위치</th>
						<th>마지막 감지 시각</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${activity.location}</td>
						<td>${activity.latest_locationtime}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br/>
		<h3>화장실 이용 빈도</h3>
		<br/>
		<div class="table-responsive">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th>오늘 이용 횟수</th>
						<th>마지막 이용 시각</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${activity.count_toilet}</td>
						<td>${activity.last_toilettime}</td>
					</tr>
				</tbody>
			</table>
		</div>

	</div>
</div>
