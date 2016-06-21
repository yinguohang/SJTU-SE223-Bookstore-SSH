<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "Entity.User" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>个人信息</title>
		<link href="./dict/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="./dict/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="./dict/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/jquery.json.min.js"></script>
	</head>
	<body>
	<%@ include file="template/UserNavbar.jsp" %>
	<%@ include file="template/Tool.jsp" %>
	
	<% 
	if (session.getAttribute("user") == null){
		response.getWriter().println("请先登录!");
		return;
	}
	User user = (User)session.getAttribute("user");
	%>	
		<script type="text/javascript">
			$(function(){
				function showPersonInfo(){
					$.ajax({
						url:"PersonInfo.jsp",
						success: function(data){
							$("#content").html(data);
							}
						});
				}
				$("#my_info").on("click", showPersonInfo);
				$("#my_book").on("click", function(){
					$.ajax({
						url:"/BookstoreSSH/DisplayBookAction",
						success: function(data){
							$("#content").html(data);
							}
						});	
					});
				$("#my_order").on("click",function(){
					$.ajax({
						url:"/BookstoreSSH/ShowOrderAction",
						success:function(data){
							$("#content").html(data);
						}
					});
				});
				$("#my_statistics").on("click",function(){
					$.ajax({
						url:"/BookstoreSSH/StatisticsPersonalAction",
						success:function(data){
							$("#content").html(data);
						}
					});
				});
				showPersonInfo();
			});
		</script>
		<div class="container" id="loadContent" style="padding-top:70px;">
			<div class="row">
				<!-- 左侧侧边栏 -->
				<div class="col-sm-12 col-md-3 col-md-offset-0 sidebar">
					 <div class="well sidebar-nav">
						<div style="padding-top:10px">
							<img  class="img-thumbnail" alt="头像暂无" src="../resource/image/icon.jpeg" style="width: 200px; height: 200px;">    
						</div>
						<h3 id="nickname_title"><%=user.getUsername() %></h3>
						<ul class="nav nav-list">
							<li><a href="#" id="my_info"><span class="glyphicon  glyphicon-user" style="margin-right:5px"></span>我的信息</a></li>
							<li><a href="#" id="my_book"><span class="glyphicon glyphicon-tasks" style="margin-right:5px"></span>我卖的书</a></li>
							<li><a href="#" id="my_order"><span class="glyphicon  glyphicon-star" style="margin-right:5px"></span>我的订单</a></li>
							<li><a href="#" id="my_statistics"><span class="glyphicon  glyphicon-bell" style="margin-right:5px"></span>我的统计</a></li>              
						</ul>
					</div>
				</div>
				<!-- 右側界面 -->
				<div class="col-sm-12 col-md-9" id="content">
					
				</div><!-- 右侧界面结束 -->
			</div><!-- row -->
		</div><!-- container -->
	</body>
</html>