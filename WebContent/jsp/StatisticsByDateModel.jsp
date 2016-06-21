<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	pageContext.setAttribute("orderList", (List)request.getAttribute("orderList"));
	pageContext.setAttribute("map", (List)request.getAttribute("mappingList"));
%>
<script type="text/javascript" src="/BookstoreSSH/jsp/dict/js/Chart.min.js"></script>
	<div>
		<div style="display:inline-block;vertical-align:top;width:20%"> 
			<h2><strong>最畅销Top10</strong></h2>
		</div>
		<div style="width: 50%; display:inline-block;vertical-align:top; padding-left:40px">
			<canvas id="canvas" height="450" width="600"></canvas>
		</div>
	</div>
	<div>
		<div style="display:inline-block;vertical-align:top;width:20%"> 
			<h2><strong>详细书籍销量</strong></h2>
		</div>
		<div style="width: 70%; display:inline-block;vertical-align:top; padding-top:20px; padding-left:40px">
			<table class="table-bordered table">
			<c:forEach var="entry" items="${map}">
				<tr>
					<td>${entry.key}</td>
					<td>${entry.value}</td>
				</tr>	
			</c:forEach>
			</table>
		</div>
	</div>
	<script>
	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
	var barChartData = {
		labels : [
<c:forEach var="entry" items="${map}">
	"${entry.key}",
</c:forEach>
			  	],
		datasets : [
			{
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill: "rgba(220,220,220,0.75)",
				highlightStroke: "rgba(220,220,220,1)",
				data : [
<c:forEach var="entry" items="${map}">
	${entry.value},
</c:forEach>
						]
			}
		]

	}
	$(function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData, {
			responsive : true
		});
	});

	</script>
	<div>
		<div style="display:inline-block;vertical-align:top;width:20%"> 
			<h2><strong>订单查询</strong></h2>
		</div>
		<div style="width: 70%; display:inline-block;vertical-align:top; padding-top:20px">
			<ul type="list-group">
			<c:forEach var="order" items="${orderList}">
				<li class="list-group-item">
				<div>
				<p>
					<strong>订单号:</strong> ${order.id} &nbsp;&nbsp;
					<strong>价格: </strong>${order.price}&nbsp;&nbsp; 
					<strong>下单日期: </strong>${order.createDate}&nbsp;&nbsp;
					<span class="pull-right">
					<button class="btn btn-primary btn-xs"
						type="button" data-toggle="collapse"
						data-target="#collapse-content-${order.id}" 
						aria-expanded="false" aria-controls="collapse-content-${order.id}"
						>
						显示内容
					</button>
					</span>
				</p>
				</div>
				<div class="collapse" id="collapse-content-${order.id}" style="width:100%">
				<table class="table-bordered table">
					<tr>
						<th>书名</th>
						<th>数量</th>
						<th>价格</th>
						<th>总价</th>
					</tr>
				<c:forEach var="book" items="${order.books}">
					<tr>
						<td>${book.key.name}</td>
						<td>${book.value}</td>
						<td>${book.key.price}</td>
						<td>${book.value*book.key.price}</td>
					</tr>
				</c:forEach>
				</table>
				</div>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>




		