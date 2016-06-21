package com.ygh;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.JSONObject;
import Biz.UserBiz;
import Biz.UserBizImpl;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* RemoveUserAction 删除用户操作
 * */

public class RemoveUserAction extends ActionSupport{
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
		if (parameters.get("userId") == null){
			json.put("status", "Error");
			json.put("message", "错误的参数!");
			content = json.toString();
			return SUCCESS;
		}
		Long userId = Long.parseLong(((String[])parameters.get("userId"))[0]);
		userBiz.removeUser(userId);
		json.put("status", "Success");
		json.put("message", "删除成功！");
		content = json.toString();
		return SUCCESS;
	}
}
