<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Entity.User" %>
<% 
	if (session.getAttribute("user") == null){
		response.getWriter().println("请先登录!");
		return;
	}
	User user = (User)session.getAttribute("user");
%>
<script type="text/javascript">
	function edit(){
		$("#button-edit").css("display", "none");
		$("#button-save").css("display", "inline");
		$("#username").attr("readonly", false);
		$("#sex-female").attr("disabled", false);
		$("#sex-male").attr("disabled", false);
		$("#mobile").attr("readonly", false);
	}
	function getSex(){
		if (document.getElementById("sex-male").checked == true)
			return 1;
		else
			return 0;
	}
	function save(){
		$("#button-edit").css("display", "inline");
		$("#button-save").css("display", "none");
		$("#username").attr("readonly", true);
		$("#sex-female").attr("disabled", true);
		$("#sex-male").attr("disabled", true);
		$("#mobile").attr("readonly", true);
		$.ajax({
			url: "/BookstoreSSH/ModifyUserProfileAction",
			data:{"username":$("#username").val(),
				"sex":getSex(),
				"mobile": $("#mobile").val()},
			success:function(data){
					var arr = $.evalJSON(data);
					showInfo(arr["message"]);
				}
			});
	}
	function cancel(){
		$("#button-edit").css("display", "inline");
		$("#button-save").css("display", "none");
		$("#username").attr("readonly", true);
		$("#sex-female").attr("disabled", true);
		$("#sex-male").attr("disabled", true);
		$("#mobile").attr("readonly", true);
		$("#username").val("");
		$("#mobile").val("");
		if (sex == 0){
			document.getElementById("sex-male").checked = false;
			document.getElementById("sex-female").checked = true;
		}else{
			document.getElementById("sex-female").checked = false;
			document.getElementById("sex-male").checked = true;
		}
	}
	$(function(){
		username = "<%=user.getUsername() %>";
		sex = <%=user.getSex() %>;
		mobile = "<%=user.getMobile() %>";
		getUserProfile();
		$("body").delegate(".removable","mouseout",function(){
				$(this).find("button").css("visibility","hidden");
			} );
		$("body").delegate(".removable","mouseover",function(){
			$(this).find("button").css("visibility","visible");
		} );
		$("body").delegate(".btn-remove","click",function(){
			var key = $(this).parents(".removable").find(".key").html();
			that = this;
			$.ajax({
				url:"/BookstoreSSH/RemoveUserProfileAction",
				data:{"key":key},
				success:function (data){
						var arr = $.evalJSON(data);
						showInfo(arr["message"]);
						$(that).parents(".removable").css("display", "none");
					}
				});
		} );
	});
	function getUserProfile(){
		$.ajax({
			url:"/BookstoreSSH/ShowUserProfileAction",
			success:function(data){
					$("#oldProfile").html(data);
				}
			});
	}
	function changePassword(){
		if ($("#new-password").val() != $("#confirm-new-password").val()){
			showInfo("两次输入密码不一致！请重新输入~");
			return;
		}
		console.log("changePassword...");
		$.ajax({
			url:"/BookstoreSSH/ChangePasswordAction",
			data:{"originalPassword":$("#original-password").val(),"newPassword": $("#new-password").val()},
			success:function(data){
					var arr = $.evalJSON(data);
					showInfo(arr["message"]);
				}
			});
	}
	function addProfile(){
		$.ajax({
			url:"BookstoreSSH/AddUserProfileAction",
			data:{"key":$("#key").val(),"value": $("#value").val()},
			success:function(data){
					var arr = $.evalJSON(data);
					showInfo(arr["message"]);
					$("#oldProfile").html($("#oldProfile").html() + '<div class="form-group removable"> <label class="col-lg-2 key control-label">'
							+ $("#key").val() + '</label> <div class="col-lg-8 value" style="padding-top:7px; padding-left:25px">' 
							+ $("#value").val() + '</div> <div class="col-lg-2" style="visibility:hidden"><button type="button" class="btn btn-success btn-remove">删除</button> </div>');
					$("#key").val("");
					$("#value").val("");
				}
			});
	}
</script>
					<div class="tabbable">
			            <ul class="nav nav-tabs">
			                <li class="active"><a href="#tab1" data-toggle="tab">我的信息</a></li>
			                <li><a href="#tab2" data-toggle="tab">密码修改</a></li>
			            </ul>
			            <div class="tab-content">
	                		<div class="tab-pane active" id="tab1">
			                    <form class="form-horizontal" id="infoForm" role="form"  style="padding-left:3%; padding-right:3%;">
				                    <div class="form-group">
				                    	<h4>基本信息
					                        <div id="button-edit" style="float:right;display:">
					                        	<button type="button" class="btn btn-success" onclick="edit()">编辑</button>
					                        </div>
					                        <div id="button-save" style="float:right;display: none">
					                        	<div class="btn-group">  
					                            	<button class="btn btn-success" type="button" id="save_info" onclick="save()" >保存</button>
					                            	<button class="btn btn-default" type="button" onclick="cancel()">取消</button>  
					                            </div> 
					                        </div>
				                        </h4>
				                   	</div>
									
					                <div class="form-group" >
					                    <label for="username" class="col-md-2 control-label">用户名</label>                                     
				                        <div class="col-md-10">
				                        	<input type="text" class="col-md-10 form-control" name="username" id="username"  value="" readonly="true"  placeholder="<%=user.getUsername() %>">
				                      	</div>
					                </div>                                                               
				                   
				                 	<div class="form-group" >                                    
				                    	<label for="sex" class="col-md-2 control-label">性别</label>
				                    	<div class="col-md-9 col-md-offset-1" > 
					                        <label class="radio-inline">
					                              <input type="radio" name="sex" value="sex-male" id="sex-male" disabled="true" style="top:20%;" <% if (user.getSex() == 1) out.print("checked"); %>/>男
					                        </label>
					                        <label class="radio-inline">
					                              <input type="radio" name="sex" value="sex-female" id="sex-female" disabled="true" style="top:20%;" <% if (user.getSex() == 0) out.print("checked"); %>/>女
					                        </label>   
				                    	</div> 
				               		</div>
		
					                <div class="form-group">
					                    <label for="mobile" class="col-md-2 control-label">手机</label>
				                        <div class="col-md-10" >
				                        	<input type="text" class="col-md-10 form-control" name="mobile" id="mobile" value=""  readonly="true"  placeholder="<%=user.getMobile() %>">
				                    	</div>
					                </div>
					                
					                <div class="form-group">
					                	<label for="mobile" class="col-md-2 control-label">当前金币</label>
					                	<div class="col-md-10" style="height:27px; align: center;padding-top:5px">
					                		<%=user.getCoin() %>
					                	</div>
					                </div>					                
			         			</form>
			         			<form class="form-horizontal" id="profileForm" role="form"  style="padding-left:3%; padding-right:3%;">
			         				<div class="form-group">
			         					<h4>个性化信息
			         						<div id="button-edit" style="float:right;display:">
					                        	<button type="button" class="btn btn-success" onclick="getUserProfile()">刷新</button>
					                        </div>	
			         					</h4>
			         				</div>
		         					<div id="oldProfile">
			         				</div>
			         				<div id="newProfile">
			         					<div class="col-lg-2">
			         						<input type="text" class="form-control" id="key" name="key"></input>
			         					</div>
			         					<div class="col-lg-8">
			         						<input type="text" class="form-control" id="value" name="value"></input>
			         					</div>
			         					<button type="button" class="btn btn-success" onclick="addProfile()">添加</button>
			         				</div>
			         			</form>
	                		</div><!-- 表1end -->
			                <div class="tab-pane" id="tab2"><p>
			                    <div class="row">
			                      	<div class="col-md-12">
			                        	<!-- <h4>修改密码</h4> -->
			                        	<form class="form-horizontal" name="setsecForm" id="setsecForm" role="form">
			                          		<div class="form-group">
			                            		<label for="original-password" class="col-lg-2 control-label">初始密码</label>
			                            		<div class="col-lg-10">
			                              			<input type="password" class="form-control" name="original-password" id="original-password" placeholder="初始密码">
			                            		</div>
			                          		</div>
				                          	<div class="form-group">
				                            	<label for="new-password" class="col-lg-2 control-label">新密码</label>
				                            	<div class="col-lg-10">
				                            	  	<input type="password" class="form-control" name="new-password" id="new-password" placeholder="新密码">
				                            	</div>
				                          	</div>
					                        <div class="form-group">
					                        	<label for="confirm-new-password" class="col-lg-2 control-label">新密码确认</label>
					                            <div class="col-lg-10">
					                            	<input type="password" class="form-control" name="confirm-new-password" id="confirm-new-password" placeholder="新密码确认">
					                            </div>
					                        </div>
			                          		<div class="form-group">
			                            		<div class="col-lg-offset-2 col-lg-10">
			                              			<button type="button" class="btn btn-success" onclick="changePassword()">确认修改</button>
			                            		</div>
			                          		</div>
			                        	</form>
			                      	</div><!-- /.col-md-12 -->
			                    </div><!-- /.row -->
			                </div><!-- 表二end -->
			            </div><!-- tab-content end -->
          			</div><!-- tabbable end -->