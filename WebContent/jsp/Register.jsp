<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册</title>
		<link href="./dict/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="./dict/jquery-1.11.2.min.js"></script>
		<script type="text/javascript" src="./dict/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/BookstoreSSH/jsp/dict/jquery.json.min.js"></script>
	</head>
	<body>
	<div class="container" style="padding-top:20px">
		<div class="col-lg-8 col-lg-offset-2">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title" align="left">
						<span>注册</span>
					</h3>
				</div>
				<div class="panel-body" style="padding-left: 5px">
					<div class="col-md-2"></div>
					<div class="col-md-8 col-xs-12">
	
						<form role="form" class="form-horizontal">
							<div class="form-group ">
	
								<div class="input-group ">
									<span class=" input-group-addon glyphicon glyphicon-user"></span>
									<input type="text" class="form-control" name="username"
										id="username" onblur="check()" placeholder="账号">
								</div>
								<font color="red"><span id="prompt_account"></span></font>
							</div>
							
							<div class="form-group ">
								<div class="input-group">
									<span class=" input-group-addon glyphicon glyphicon-lock"></span>
									<input type="password" class="form-control" name="password"
										id="password" onblur="checkPassword()" placeholder="密码6~20位">
								</div>
								<font color="red"><span id="prompt_password"></span></font>
							</div>
	
	
							<div class="form-group ">
								<div class="input-group">
									<span class=" input-group-addon glyphicon glyphicon-ok"></span>
									<input type="password" class="form-control" name="sure_password"
										id="sure_password" onblur="checkSurePassword()"
										placeholder="确认密码">
								</div>
								<font color="red"><span id="prompt_sure_password"></span></font>
							</div>
	
							<!--手机号码-->
							<div class="form-group ">
								<div class="input-group">
									<span class=" input-group-addon glyphicon glyphicon-phone"></span>
									<input type="text" class="form-control" name="mobile"
										id="mobile" onblur="checkMobile()" pattern="^\+?[1-9][0-9]*$"
										placeholder="手机号码">
								</div>
								<font color="red"><span id="prompt_mobile"></span></font>
							</div>
							
							<!--性别-->	
							<div class="form-group">
								<label class="control-label " for="sex">性别 </label> 
								<label class="radio-inline" style="margin-left:2px"> 
									<input type="radio" name="sex" id="sex_male"/>男
								</label> 
								<label class="radio-inline"> 
									<input type="radio" name="sex" id="sex_female">女
								</label>
							</div>	
						</form>
						<div class="form-group " style="padding-left: 45%">
							<button class="btn btn-primary" onclick="return checkform()"
								id="sub">提交</button>
						</div>
					</div>
				</div><!--面板内容结束-->
			</div>
		</div>
		</div>
	</body>
	<script type="text/JavaScript">
	function validatemobile(mobile){           
		//手机号码格式是否正确
   		if(mobile.length!=11){
       		return false;
    	}
    	var myreg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	    if(!myreg.test(mobile)){
	    	return false;
	    }
	    return true;
	}
	function checkradio(){
		//判断单选框是否已选
		var temp=document.getElementsByName('sex');
		for (i=0;i<temp.length;i++){
			if(temp[i].checked){
				return true;
			}
	  	}
	  	return false; 
	}
	function check(){
		//检查username是否已经被注册
		var username=document.getElementById('username').value;
		$.ajax({
			type:"POST",
			url:"/BookstoreSSH/IsExistedAccountAction",
			data:{"username":username},
			success:function(data){
				var arr = $.evalJSON(data);
				if(arr["status"] != "Available"){
					document.getElementById('prompt_account').innerHTML=arr["message"];
				    return false;
				}else{
					document.getElementById('prompt_account').innerHTML=arr["message"];
				    return true;					
				}
				},
			error:function(){
				alert("出现不知名错误");
				return false;
			}
        });	
	}
	
	
	function checkPassword(){
	    if(document.getElementById('password').value.length>20||document.getElementById('password').value.length<6){
			document.getElementById('prompt_password').innerHTML="　　密码长度应为6~20位";
			return false;
		}
		else{
			document.getElementById('prompt_password').innerHTML="";
	        return true;		
		}
		
	}
	
	function checkSurePassword(){
		var pass1=document.getElementById('password').value;
		var pass2=document.getElementById('sure_password').value;
		if(pass1!==pass2){
			document.getElementById('prompt_sure_password').innerHTML="　　密码不一致！";
			return false;
		}else{
			document.getElementById('prompt_sure_password').innerHTML="";
			return true;
		}	
	}
	function checkMobile(){
		var mobile=document.getElementById('mobile').value;
		if(!validatemobile(mobile)){
			document.getElementById('prompt_mobile').innerHTML="　　请输入正确的手机号码！";
			return false;
		}else{
			document.getElementById('prompt_mobile').innerHTML="";
			return true;
		}
	}
	
	function checkform(){	
		//判断表单是否为空
		if(document.getElementById('username').value.length==0){
			alert('账号不能为空！');
			document.getElementById('username').focus();
			return false;
		}
		if(document.getElementById('password').value.length>20||document.getElementById('password').value.length<6){
			alert('密码不能为空！且长度6~20位');
			document.getElementById('sure_password').focus();
			return false;
		}
		if(document.getElementById('sure_password').value.length==0){
			alert('确认密码不能为空！');
			document.getElementById('password').focus();
			return false;
		}
		if(document.getElementById('mobile').value.length==0){
			alert('手机不能为空！');
			document.getElementById('mobile').focus();
			return false;
		}
		if(!checkradio()){
			alert('请选择性别');
			return false;
		}

		//账号是否可以注册
		if(check()){
			alert('您注册的账号已经存在！');
			document.getElementById('username').focus();
			return false;
		}
			
		//判断密码是否一致	
		var pass1=document.getElementById('password').value;
		var pass2=document.getElementById('sure_password').value;
			if(pass1!==pass2){
			alert('密码不一致！请重新输入');
			document.getElementById('sure_password').focus();
			return false;
			}
	    
	    //账号长度必须在4到20位之间
	    var username=document.getElementById('username').value;
	    if(username.length<4||username.length>16){
	        alert("账号长度应在4—16位之间");
			document.getElementById('username').focus();
			return false;
	    }
	    
	    //判断手机号码
		var mobile=document.getElementById('mobile').value;
		if(!validatemobile(mobile)){
			alert('请输入正确的手机号码！');
			document.getElementById('mobile').focus();
			return false;
		}
		submit();
	}
	 
	
	function submit(){
		var sex=0;
		if(document.getElementById('sex_male').checked){
			sex=1;
		}
		$.ajax({
			type:"GET",
			url:"/BookstoreSSH/RegisterAction",
			data:{
				"username":document.getElementById('username').value,
				"password":document.getElementById('password').value,
				"mobile":document.getElementById('mobile').value,
				"sex":sex
			},
		    success:function(data){
				empty();
				var arr = $.evalJSON(data);
				$("#modal-content").text(arr["message"]);
				$("#modal").modal("show");
			},
			error:function(data){
				$("#modal-content").text("出现异常!");
				$("#modal").modal("show");
		}
	    });	
		function empty(){
			document.getElementById('username').value="";
			document.getElementById('password').value="";
			document.getElementById('sure_password').value="";
			document.getElementById('mobile').value="";
		}
	}
	</script>
	
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
		        <a type="button" class="btn btn-primary" role="button" href="Center.jsp">进入大厅</a>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	
</html>