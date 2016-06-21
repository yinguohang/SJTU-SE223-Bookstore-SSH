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

/* ModifyUserAction 修改用户信息
 * */

public class ModifyUserAction extends ActionSupport{
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
	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		Map<String, Object> hSession = context.getSession();
		JSONObject json = new JSONObject();
		if (parameters.get("userId") == null){
			json.put("status", "Error");
			json.put("status", "错误的参数!");
			content = json.toString();
			return SUCCESS;
		}
		if (parameters.get("priority") != null){
			if (((String[])parameters.get("priority"))[0] != ""){
				int temp = Integer.parseInt(((String[])parameters.get("priority"))[0]);
				if (temp != 1){
					parameters.remove("priority");
				}
			}
		}
		userBiz.ModifyUser(parameters);
		json.put("status", "Success");
		json.put("message", "更改成功！");
		content = json.toString();
		return SUCCESS;
	}
	
}
