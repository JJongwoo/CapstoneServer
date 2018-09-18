<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
	<div class="container">
		<h3>최근 3일간 화장실 이용내역<a href="javascript:history.back()"><i
				class="fa fa-arrow-circle-left fa-pull-right"
				style="color: black; font-size: 20px;">뒤로가기</i></a></h3>
		<div class="col-xs-3">
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th>날짜</th>
						<th>화장실 이용 횟수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${dayone}</td>
						<td>${countone}</td>
					</tr>
					<tr>
						<td>${daytwo}</td>
						<td>${counttwo}</td>
					</tr>
					<tr>
						<td>${daythree}</td>
						<td>${countthree}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-xs-3">
			<canvas id="myChart"></canvas>
		</div>
	</div>
</div>

<script>
	var ctx = document.getElementById("myChart").getContext('2d');

	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : [ "${daythree}", "${daytwo}", "${dayone}" ],
			datasets : [ {
				label : '화장실 이용 횟수',
				data : [ "${countthree}", "${counttwo}", "${countone}" ],
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)' ],
				borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
						'rgba(255, 206, 86, 1)' ],
				borderWidth : 1
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});
</script>

