<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	pageContext.setAttribute("orderList", (List)request.getAttribute("orderList")); 
%>

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

		