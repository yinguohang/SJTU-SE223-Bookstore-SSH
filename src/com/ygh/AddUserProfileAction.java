package com.ygh;

import java.util.Map;

import net.sf.json.JSONObject;
import Biz.UserBiz;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* AddUserProfileAction 添加用户自定义属性
 * */

public class AddUserProfileAction extends ActionSupport{
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private UserBiz userBiz;
	public UserBiz getUserBiz() {
		return userBiz;
	}
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	public String execute() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		Map<String, Object> hSession = context.getSession();
		JSONObject json = new JSONObject();
		if (parameters.get("key") == null ||
				parameters.get("value") == null ||
				((String[])parameters.get("key"))[0] == "" ||
				((String[])parameters.get("value"))[0] == "" ){
			json.put("status", "Error");
			json.put("message", "参数不全或参数为空！");
			content = json.toString();
			return SUCCESS;
		}
		userBiz.addUserProfileItem((User)hSession.get("user"), ((String[])parameters.get("key"))[0], ((String[])parameters.get("value"))[0]);
		json.put("status", "Success");
		json.put("message", "添加成功！");
		content = json.toString();
		return SUCCESS;
	}
}
