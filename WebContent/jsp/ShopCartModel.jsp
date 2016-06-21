<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	pageContext.setAttribute("arrayList", (ArrayList)session.getAttribute("arrayList")); 
%>

<script type="text/javascript" src="/BookstoreSSH/jsp/dict/jquery.json.min.js"></script>
<script type="text/javascript">
	function addBookNum(bookId, bookNum){
		$.ajax({
			url:"/BookstoreSSH/AddBookToShopCartAction",
			data:{"bookId":bookId,"bookNum":bookNum},
			success: function(data){	
				var num = parseInt($("#number"+bookId).text()) + parseInt(bookNum);
				if (num>0)
					$("#number"+bookId).text(num);
				else
					$("#tr"+bookId).hide();
			}
		});
	}
</script>

<table class="table">
	<tr>
		<th>书名</th>
		<th>作者</th>
		<th>数量</th>
		<th>价格</th>
		<th>总价</th>
	</tr>
<c:forEach var="item" items="${arrayList}">
	<tr id="tr${item.book.id}">
		<td>${item.book.name} </td>
		<td>${item.book.author} </td> 
		<td>
			<button class="btn btn-success" style="padding:2px"
				onclick="addBookNum(${item.book.id},-1)">
				<span class="glyphicon glyphicon-minus"></span>
			</button>
			<span id="number${item.book.id}">${item.bookNum}</span>
			<button class="btn btn-success" style="padding:2px"
				onclick="addBookNum(${item.book.id},1)">
				<span class="glyphicon glyphicon-plus"></span>
			</button>
		</td>
		<td>${item.book.price} </td> 
		<td>${item.bookNum*item.book.price} </td>
	</tr>
</c:forEach>
</table>

<script type="text/javascript">
	function createOrder(){
		$.ajax({
			url:"/BookstoreSSH/CreateOrderAction",
			success:function(data){
				getShopCart();
				var arr = $.evalJSON(data);
				showInfo(arr["message"]);
			},
			error:function(data){
				showInfo("下单失败!");
			}
		});
	}
</script>
<div style="padding-left: 45%">
	<button class="btn btn-primary" id="createOrder" onclick="createOrder()">下单</button>
</div>