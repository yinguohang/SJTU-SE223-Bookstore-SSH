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
		<link href="/BookstoreSSH/jsp/dict/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/js/bootstrap-datetimepicker.js"></script>
	</head>
	<%@include file="template/UserNavbar.jsp" %>
	<body style="padding-top: 70px">
		<div class="container">
			<div class="row">
				<div class="col-lg-5">
					<div class="form-group">
			            <label for="start-date" class="col-lg-4 control-label">开始日期</label>
			            <div class="input-group date form_date col-lg-8" data-date="" data-date-format="dd MM yyyy" data-link-field="start-date" data-link-format="yyyy-mm-dd">
			            	<input class="form-control" size="16" type="text" value="" readonly>
			                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
						<input type="hidden" id="start-date" value="" /><br/>
			        </div>
		        </div>           
				<div class="col-lg-5">
					<div class="form-group">
			            <label for="end-date" class="col-lg-4 control-label">结束日期</label>
			            <div class="input-group date form_date col-lg-8" data-date="" data-date-format="dd MM yyyy" data-link-field="end-date" data-link-format="yyyy-mm-dd">
			            	<input class="form-control" size="16" type="text" value="" readonly>
			                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			            </div>
						<input type="hidden" id="end-date" value="" /><br/>
			        </div>
		        </div>
		        <div class="col-lg-2">
		        	<button type="button" class="btn btn-success"  onclick="query()">查询</button>
		        </div>
	        </div>
	        <div id="content">
	        </div>
	        <!-- Footer -->
			<div class="container">
		        <hr>
		        <footer>
		            <div class="row">
		                <div class="col-lg-12">
		                    <p align="center">Copyright &copy; Bookstore 2015</p>
		                </div>
		            </div>
		        </footer>
		    </div>
	    </div>
		<script type="text/javascript">
		    $('.form_date').datetimepicker({
		        language:  'fr',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
		    function query(){
				console.log($("#start-date").val());
				console.log($("#end-date").val());
				$.ajax({
					url:"/BookstoreSSH/StatisticsByDateAction",
					data:{"startDate":$("#start-date").val(),
						"endDate":$("#end-date").val()},
					success:function(data){
							$("#content").html(data);
						}
					});	
			}
		</script>
	</body>
</html>