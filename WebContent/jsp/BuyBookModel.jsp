<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Entity.Book" %>
<%@ page import="java.util.HashMap" %>

<% 
	Book[] bookArray = (Book[])request.getAttribute("bookArray");
	HashMap shopCart = (HashMap)session.getAttribute("shopCart");
%>

<script type="text/javascript">
	function addBook(bookId){
		$.ajax({
			url:"/BookstoreSSH/AddBookToShopCartAction",
			data:{"bookId":bookId},
			success: function(data){		
				$("#number"+bookId).text(parseInt($("#number"+bookId).text()) + 1);
				$("#number"+bookId).show();
			}
		});
	}
</script>

<style>
	.no-border{
		border-top: 0px!important;
	}
	.label-name{
		width:30%;
	}
</style>

<div class="row" id="books">
<%
	for (int i = 0; i < bookArray.length; i++){
%>
					<div class="col-sm-12 col-lg-6 col-md-6" style="padding:15px">
                        <div style="display:table-cell;vertical-align:top">
                            <img src="/BookstoreSSH/resource/image/book.jpg" style="width:180px; height:220px">
                        </div>
                        <div style="display:table-cell;vertical-align:top;width:100%">
                            <div style="padding:5px">
	                            <!-- 
	                            <p>
	                            	<span><strong>书名:</strong></span><span><%=bookArray[i].getName() %></span>
	                            </p>
	                            <p>
	                            	<span><strong>作者:</strong></span><span><%=bookArray[i].getAuthor() %></span>
	                            </p>
	                            <p>
	                            	<span><strong>出版社:</strong></span><span><%=bookArray[i].getPublisher() %></span>
	                            </p>
	                            <p>
	                            	<span><strong>价格:</strong></span><span><%=bookArray[i].getPrice() %></span>
	                            </p>
	                            <p>
	                            	<span><strong>库存:</strong></span><span><%=bookArray[i].getStock() %></span>
	                            </p>
	                            <p>
	                            	<span><strong>卖家:</strong></span><span><%=bookArray[i].getSeller().getUsername() %></span>
	                            	<span class="pull-right">
	                            		<button class="btn btn-success" onclick="addBook(<%=bookArray[i].getId() %>)"><span class="glyphicon glyphicon-shopping-cart"></span></button>
	                            	</span>
	                            	<% if (shopCart != null && shopCart.containsKey(bookArray[i].getId())) {%>
	                            		<span id="number<%=bookArray[i].getId() %>" class="pull-right badge" style="background-color:#3A5FCD"><%=shopCart.get(bookArray[i].getId())%></span>
	                            	<%}else{%>
	                            		<span id="number<%=bookArray[i].getId() %>" class="pull-right badge" style="background-color:#3A5FCD;display:none"><%=0%></span>
	                            	<%}%>
	                            </p>
	                            -->
	                            <table class="table">
	                            	<tr>
	                            		<td class="no-border label-name"><strong>书名:</strong></td>
	                            		<td class="no-border colorable"><%=bookArray[i].getName() %></td>
	                            	</tr>
		                            <tr>
	                            		<td class="no-border label-name"><strong>作者:</strong></td>
	                            		<td class="no-border colorable"><%=bookArray[i].getAuthor() %></td>
	                            	</tr>
	                            	<tr>
	                            		<td class="no-border label-name"><strong>出版社:</strong></td>
	                            		<td class="no-border colorable"><%=bookArray[i].getPublisher() %></td>
	                            	</tr>
	                            	<tr>
	                            		<td class="no-border label-name"><strong>价格:</strong></td>
	                            		<td class="no-border"><%=bookArray[i].getPrice() %></td>
	                            	</tr>
	                            	<tr>
	                            		<td class="no-border label-name"><strong>库存:</strong></td>
										<td class="no-border"><%=bookArray[i].getStock() %></td>
	                            	</tr>
	                            	<tr>
	                            		<td class="no-border label-name"><strong>卖家:</strong></td>
	                            		<td class="no-border"><%=bookArray[i].getSeller().getUsername() %>
	                            	<span class="pull-right">
	                            		<button class="btn btn-success" onclick="addBook(<%=bookArray[i].getId() %>)"><span class="glyphicon glyphicon-shopping-cart"></span></button>
	                            	</span>
	                            	<% if (shopCart != null && shopCart.containsKey(bookArray[i].getId())) {%>
	                            		<span id="number<%=bookArray[i].getId() %>" class="pull-right badge" style="background-color:#3A5FCD"><%=shopCart.get(bookArray[i].getId())%></span>
	                            	<%}else{%>
	                            		<span id="number<%=bookArray[i].getId() %>" class="pull-right badge" style="background-color:#3A5FCD;display:none"><%=0%></span>
	                            	<%}%>
	                            		</td>
	                            	</tr>
	                            </table>	                            
                            </div>                            
                        </div>
                    </div>		
<%
	}
%>
</div>