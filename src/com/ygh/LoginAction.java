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

/* LoginAction 登陆操作
 * */

public class LoginAction extends ActionSupport{
	private UserBiz userBiz;
	private String content;
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
	@Override
	public String execute() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		JSONObject json = new JSONObject();
		if (parameters.get("username") == null || parameters.get("password") == null) {
			json.put("status", "Error");
			json.put("message", "用户名或密码为空！");
			setContent(json.toString());
			return SUCCESS;
		}
		String username = ((String[]) parameters.get("username"))[0];
		String password = ((String[]) parameters.get("password"))[0];
		User user = userBiz.getUserByUsernameAndPassword(username, password);
		if (user != null){
			json.put("status", "Success");
			json.put("message", "恭喜您登陆成功！");
			Map<String, Object> hSession = context.getSession();
			hSession.put("user", user);
		}else{
			json.put("status", "Wrong");
			json.put("message", "错误的用户名及密码！");
		}
		setContent(json.toString());
		return SUCCESS;
	}
}
