<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Entity.Book" %>
<%@ page import="java.util.HashMap" %>
<script type="text/javascript" src="/BookstoreSSH/jsp/dict/jquery.json.min.js"></script>
<% 
	Book[] bookArray = (Book[])request.getAttribute("bookArray");
	HashMap shopCart = (HashMap)session.getAttribute("shopCart");
%>

<script type="text/javascript" src="/BookstoreSSH/jsp/js/OwnBookModel.js"></script>

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
					<div class="col-sm-12 col-lg-6 col-md-6 book-div" style="padding:15px">
                        <div style="display:table-cell;vertical-align:top">
                            <img src="/BookstoreSSH/resource/image/book.jpg" style="width:180px; height:220px">
                        </div>
                        <div style="display:table-cell;vertical-align:top;width:100%">
                            <div style="padding:5px">
	                            <table class="table" id="book<%=bookArray[i].getId() %>">
	                            	<tr>
	                            		<td class="no-border label-name"><strong>书名:</strong></td>
	                            		<td class="no-border colorable name"><%=bookArray[i].getName() %></td>
	                            	</tr>
		                            <tr>
	                            		<td class="no-border label-name"><strong>作者:</strong></td>
	                            		<td class="no-border colorable author"><%=bookArray[i].getAuthor() %></td>
	                            	</tr>
	                            	<tr>
	                            		<td class="no-border label-name"><strong>出版社:</strong></td>
	                            		<td class="no-border colorable publisher"><%=bookArray[i].getPublisher() %></td>
	                            	</tr>
	                            	<tr>
	                            		<td class="no-border label-name"><strong>价格:</strong></td>
	                            		<td class="no-border price"><%=bookArray[i].getPrice() %></td>
	                            	</tr>
	                            	<tr>
	                            		<td class="no-border label-name"><strong>库存:</strong></td>
										<td class="no-border stock"><%=bookArray[i].getStock() %></td>
	                            	</tr>
	                            	<tr>
	                            		<td class="no-border label-name"><strong>卖家:</strong></td>
	                            		<td class="no-border seller"><%=bookArray[i].getSeller().getUsername() %>
	                            		<span>
	                            			<button class="btn btn-success btn-xs pull-right button-remove" style="margin-left:5px">删除</button>
	                            			<button class="btn btn-success btn-xs pull-right button-change">修改</button>
	                            		</span>
	                            		</td>
	                            	</tr>
	                            	<tr style="display:none">
	                            		<td class="no-border label-name"><strong>ID:</strong></td>
										<td class="no-border id"><%=bookArray[i].getId() %></td>
	                            	</tr>
	                            </table>	                            
                            </div>                            
                        </div>
                    </div>		
<%
	}
%>
</div>


    	<div id="modal-form" class="modal fade">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">信息</h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal" method="post" id="form">							
							<div class="form-group ">
								<label for="name" class="col-sm-2 control-label">书名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="name" id="name" placeholder="请输入书名"/>
								</div>
							</div>
							
							<div class="form-group ">
								<label for="author" class="col-sm-2 control-label">作者</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="author" id="author" placeholder="请输入作者"/>
								</div>
							</div>
							
							<div class="form-group ">
								<label for="publisher" class="col-sm-2 control-label">出版社</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="publisher" id="publisher" placeholder="请输入出版社"/>
								</div>
							</div>
							
							<div class="form-group ">
								<label for="price" class="col-sm-2 control-label">价格</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="price" id="price" placeholder="请输入价格"/>
								</div>
							</div>
							
							<div class="form-group ">
								<label for="stock" class="col-sm-2 control-label">库存</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="stock" id="stock" placeholder="请输入库存"/>
								</div>
							</div>
						</form>
		      </div>
		      <div class="modal-footer">
		      	<button class="btn btn-primary" id="submit" onclick="submit()">提交</button>
		        <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		    <script type="text/javascript">
		function showInfo(info){
			$("#modal-content").text(info);
			$("#modal").modal("show");
		}
    </script>
    
    <!-- /.container -->
    <div id="modal" class="modal fade">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">信息</h4>
		      </div>
		      <div class="modal-body">
		        <p id="modal-content"></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->