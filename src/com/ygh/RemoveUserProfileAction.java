package com.ygh;

import java.util.Map;

import net.sf.json.JSONObject;
import Biz.UserBiz;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* RemoveUserProfileAction 删除用户个性化信息
 * */

public class RemoveUserProfileAction extends ActionSupport{
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
				((String[])parameters.get("key"))[0] == ""){
			json.put("status", "Error");
			json.put("message", "参数不全或参数为空！");
			content = json.toString();
			return SUCCESS;
		}
		userBiz.removeUserProfileItem((User)hSession.get("user"), ((String[])parameters.get("key"))[0]);
		json.put("status", "Success");
		json.put("message", "删除成功！");
		content = json.toString();
		return SUCCESS;
	}
}
