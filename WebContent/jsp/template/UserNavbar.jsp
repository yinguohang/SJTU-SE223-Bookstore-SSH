<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Entity.User" %>
<% if (session.getAttribute("user") != null) {
	User user = (User)session.getAttribute("user");
	%>
	<!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Bookstore</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/BookstoreSSH/jsp/Info.jsp">个人信息</a>
                    </li>
                    <li>
                        <a href="/BookstoreSSH/jsp/Center.jsp">书目大厅</a>
                    </li>
                    <li>
                        <a href="#">联系我们</a>
                    </li>
                    <% if (user.getPriority() == 1) {%>
                    <li>
                        <a href="/BookstoreSSH/ShowAllUserAction">管理用户</a>
                    </li>
                    <li>	
                    	<a href="/BookstoreSSH/jsp/StatisticsSaleModel.jsp">销售统计</a>
                    </li>
                    <%} %>
                    <li>
                        <a href="/BookstoreSSH/LogOutAction">登出(<%=user.getUsername() %>)</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
<%}else{%>
	<!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Bookstore</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/BookstoreSSH/jsp/Info.jsp">个人信息</a>
                    </li>
                    <li>
                        <a href="/BookstoreSSH/jsp/Center.jsp">书目大厅</a>
                    </li>
                    <li>
                        <a href="#">联系我们</a>
                    </li>
                    <li>
                        <a href="/BookstoreSSH/jsp/Welcome.jsp">登陆</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
<%}%>