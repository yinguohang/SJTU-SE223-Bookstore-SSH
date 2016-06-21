package com.ygh;

import java.util.Map;

import net.sf.json.JSONObject;
import Biz.UserBiz;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* ChangePasswordAction 修改密码操作
 * */

public class ChangePasswordAction extends ActionSupport{
	private String content;
	private UserBiz userBiz;
	public UserBiz getUserBiz() {
		return userBiz;
	}
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String execute() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		Map<String, Object> hSession = context.getSession();
		JSONObject json = new JSONObject();
		if (parameters.get("originalPassword") == null ||
				parameters.get("newPassword") == null ||
				((String[])parameters.get("originalPassword"))[0] == "" ||
				((String[])parameters.get("newPassword"))[0] == "" ){
			json.put("status", "Error");
			json.put("message", "缺少参数或参数为空！");
			content = json.toString();
			return SUCCESS;
		}
		String originalPassword = ((String[])parameters.get("originalPassword"))[0];
		String newPassword = ((String[])parameters.get("newPassword"))[0];
		User user = (User)hSession.get("user");
		if (userBiz.changePassword(user, originalPassword, newPassword)){
			json.put("status", "Success");
			json.put("message", "修改成功！");
			content = json.toString();
			return SUCCESS;
		}else{
			json.put("status", "Wrong");
			json.put("message", "修改失败，原密码不正确！");
			content = json.toString();
			return SUCCESS;
		}
	}
}
