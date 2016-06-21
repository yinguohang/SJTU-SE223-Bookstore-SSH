<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Entity.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>书目大厅</title>
		<link href="/BookstoreSSH/jsp/dict/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/jquery.json.min.js"></script>
	</head>
	<body style="padding-top:50px">
<%@ page import="Entity.User" %>
<%@ page import="java.util.HashMap" %>

<% 
	User[] userArray = (User[])request.getAttribute("userArray");
	int[] orderNum = (int[])request.getAttribute("orderNum");
%>

<%@include file="template/UserNavbar.jsp" %>

<script type="text/javascript">
	function submit(){
		$("#modal-form").modal("hide");
		$.ajax({
			url: "/BookstoreSSH/ModifyUserAction",
			data:{"userId":$("#userId").text(),
				"coin":$("#coin").val(),
				"mobile":$("#mobile").val(),
				"priority":$("#priority").val()
				},
			success:function(data){
					var arr = $.evalJSON(data);
					if (arr.hasOwnProperty("warning"))
						showInfo(arr["warning"] + arr["message"]);
					else
						showInfo(arr["message"]);
					$("#tr"+$("#userId").text()).find(".coin").text($("#coin").val());
					$("#tr"+$("#userId").text()).find(".mobile").text($("#mobile").val());
					$("#tr"+$("#userId").text()).find(".priority").text($("#priority").val());
				} 
			});
	}
	$(function(){
		$(".mouse-over-show").css("visibility","hidden");
		$(".delete-button").click(function(){
				var that = this;
				$.ajax({
					url: "/BookstoreSSH/RemoveUserAction",
					data: {"userId": $(this).parents(".tr").children(".id").text()},
					success:function(data){
							var arr = $.evalJSON(data);
							$(that).parents(".tr").css("display", "none");
							showInfo(arr["message"]);	
						} 
					});
			});
		$(".modify-button").click(function(){
			$("#userId").text($(this).parents("tr").children(".id").text());
			$("#userName").text($(this).parents("tr").children(".username").text());
			$("#coin").val($(this).parents("tr").children(".coin").text());
			$("#mobile").val($(this).parents("tr").children(".mobile").text());
			$("#priority").val($(this).parents("tr").children(".priority").text());
			$("#modal-form").modal("show");
		});
		$(".tr").mouseover(function(){
				$(this).find(".mouse-over-show").css("visibility","visible");
			});
		$(".tr").mouseout(function(){
				$(this).find(".mouse-over-show").css("visibility","hidden");
			});	
	});
	
</script>
<div class="container">
<p style="color:#aaaaaa">ps: 性别: 1->男,0->女; 权限: 1->管理员,0->普通用户</p>
<table class="table">
	<tr>
		<th>ID</th>
		<th>用户名</th>
		<th>性别</th>
		<th>金币</th>
		<th>手机号</th>
		<th>创建日期</th>
		<th>权限</th>
		<th>订单总数</th>
		<th></th>
	</tr>
<% for (int i = 0; i < userArray.length; i++){%>
	<tr class="tr" id="tr<%=userArray[i].getId()%>">
		<td class="id"><%=userArray[i].getId()%></td>
		<td class="username"><%=userArray[i].getUsername()%></td> 
		<td class="sex"><%=userArray[i].getSex()%></td> 
		<td class="coin"><%=userArray[i].getCoin()%></td> 
		<td class="mobile"><%=userArray[i].getMobile()%></td> 
		<td class="createDate"><%=userArray[i].getCreateDate()%></td>
		<td class="priority"><%=userArray[i].getPriority()%></td>
		<td class="orderNumber"><%=orderNum[i]%></td>		 
		<td>
			<button class="btn btn-sm btn-success pull-right mouse-over-show delete-button" style="margin-left:2px">删除</button>
			<button class="btn btn-sm btn-success pull-right mouse-over-show modify-button">修改</button>
		</td>
	</tr>
<%} %>
</table>
</div>

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

	<!-- /.container -->
    <div id="modal-form" class="modal fade">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">信息</h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal" method="post" id="form">							
							<div class="form-group ">
								<label class="col-sm-2 control-label">ID</label>
								<div class="col-sm-10">
									<p id="userId" style="padding-top:5px"></p>
								</div>
							</div>
							
							<div class="form-group ">
								<label class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-10">
									<p id="userName" style="padding-top:5px"></p>
								</div>
							</div>
							
							<div class="form-group ">
								<label for="coin" class="col-sm-2 control-label">金币数</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="coin" id="coin" placeholder="请输入金币数"/>
								</div>
							</div>
							
							<div class="form-group ">
								<label for="price" class="col-sm-2 control-label">手机号</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="mobile" id="mobile" placeholder="请输入手机号"/>
								</div>
							</div>
							
							<div class="form-group ">
								<label for="priority" class="col-sm-2 control-label">权限</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="priority" id="priority" placeholder="请输入权限"/>
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


</body>