<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>欢迎|登陆</title>
		<link href="/BookstoreSSH/jsp/dict/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/jquery.json.min.js"></script>
	</head>
	<body>
		<style type="text/css">
		.transparent_class {
				filter:alpha(opacity=50);
				-moz-opacity:0.5;
				-khtml-opacity: 0.5;
				opacity: 0.5;
			}
		</style>
		<div id="background" style="position:absolute;height:100%;width:100%;z-index:-1;padding:0px;">    
			<img src="/BookstoreSSH/resource/image/bg.jpg" height="100%" width="100%"/>    
		</div>
		<script type="text/javascript">
			function login(){
				$.ajax({
					url: "/BookstoreSSH/LoginAction", 
					data: $("#info-form").serialize(),
					type:"Get",
					success: function(data){
						var arr = $.evalJSON(data);
						if (arr["status"] == "Success"){
							$("#modal-content").text(arr["message"]);
							$("#entry").show();
							$("#modal").modal("show");
						}else{
							$("#modal-content").text(arr["message"]);
							$("#entry").hide();
							$("#modal").modal("show");
						}					
					}
				});
			}
		</script>
		<style type="text/css">
		</style>
		<div class="col-lg-4 col-lg-offset-4 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3" style="padding-top:100px">
			<div class="panel panel-default" style="background:rgba(255,255,255,0.6)">
				<div class="panel-body">
					<form class="form-horizontal" id="info-form" method="post">
						<div class="form-group" >
							<label for="username" class="col-sm-3 control-label">用户名:</label>
							<div class="col-sm-9">
								<input id="username" type="text" id = "username" name="username" class="form-control" placeholder="请输入您的用户名"/>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">密码:</label>
							<div class="col-sm-9">
								<input id="password" type="password" id = "password" name="password" class="form-control" placeholder="请输入您的密码"/>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-2 col-lg-offset-3 col-md-2 col-md-offset-3 col-sm-2 col-sm-offset-3 col-xs-2 col-xs-offset-3">
								<input type="button" class="btn btn-success" value="登陆" id="login-button" onclick="login()"/>
							</div>
							<div class="col-lg-2 col-lg-offset-2 col-md-2 col-md-offset-3 col-sm-2 col-sm-offset-3 col-xs-2 col-xs-offset-2">
								<a type="button" class="btn btn-default" href="/BookstoreSSH/jsp/Register.jsp">注册</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<div id="modal" class="modal fade">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title">信息</h4>
		      </div>
		      <div class="modal-body">
		        <p id="modal-content"></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">确认</button>
		        <a id="entry" type="button" class="btn btn-primary" role="button" href="/BookstoreSSH/jsp/Center.jsp">进入大厅</a>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>
</html>