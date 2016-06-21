<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<script type="text/javascript">
			function submit(){
				$("#form").submit();
			}
		</script>
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title" align="left">
						<span>书籍信息</span>
					</h3>
				</div>
				<div class="panel-body" style="padding-left: 5px">
					<div class="col-md-2"></div>
					<div class="col-md-8 col-xs-12">
						<form class="form-horizontal" action="/BookstoreSSH/AddNewBookAction" method="post" id="form">							
							<div class="form-group ">
								<label for="name" class="col-sm-2 control-label">书目名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="name" id="name" placeholder="请输入书目名称"/>
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
							
							<div class="form-group ">
								<label for="type" class="col-sm-2 control-label">类别</label>
								<div class="col-sm-10">
									<select name="type" class="form-control">
									  <option value="0">小说</option>	
									  <option value="1">教材</option>
									  <option value="2">参考书</option>
									  <option value="3">经典阅读</option>
									</select>
								</div>
							</div>
							
							<div class="form-group " style="padding-left: 45%">
								<button class="btn btn-primary" id="submit" onclick="submit()">提交</button>
							</div>
						</form>
					</div>
				</div><!--面板内容结束-->
			</div>
		</div>