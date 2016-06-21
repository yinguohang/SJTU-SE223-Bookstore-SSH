package com.ygh;
import java.util.Date;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.JSONObject;
import Biz.UserBiz;
import Biz.UserBizImpl;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* RegisterAction 注册操作
 * */

public class RegisterAction extends ActionSupport{
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
		if (parameters.get("username") == null ||
				parameters.get("password") == null ||
				parameters.get("mobile") == null ||
				parameters.get("sex") == null){
			json.put("status", "error");
			json.put("message", "有未填入的信息，请检查！");
			content = json.toString();
			return SUCCESS;
		}
		String username = ((String[])parameters.get("username"))[0];
		String password = ((String[])parameters.get("password"))[0];
		long mobile = Long.parseLong(((String[])parameters.get("mobile"))[0]);
		int sex = Integer.parseInt(((String[])parameters.get("sex"))[0]);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setSex(sex);
		user.setCoin(1000);
		user.setMobile(mobile);
		user.setPriority(0);
		Date date = new Date();
		user.setCreateDate(date);
		if (userBiz.addUser(user)){
			hSession.put("user", user);
			json.put("status", "success");
			json.put("message", "恭喜注册成功！");
			content = json.toString();
		}else{
			json.put("status", "error");
			json.put("message", "用户名已被使用！");
			content = json.toString();
		}
		return SUCCESS;
	}
}
