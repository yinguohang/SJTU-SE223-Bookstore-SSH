package com.ygh;

import java.util.Map;

import net.sf.json.JSONObject;
import Biz.UserBiz;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* ModifyUserProfileAction 修改用户个性化信息
 * */

public class ModifyUserProfileAction extends ActionSupport{
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
				parameters.get("sex") == null ||
				parameters.get("mobile") == null){
			json.put("status", "Error");
			json.put("message", "缺少参数！");
			content = json.toString();
			return SUCCESS;
		}
		User user = (User)hSession.get("user");
		String[] str= new String[1];
		str[0] = Long.toString(user.getId());
		parameters.put("userId", str);
		if (((String[])parameters.get("username"))[0] == "" &&
				((String[])parameters.get("sex"))[0] == "" &&
				((String[])parameters.get("mobile"))[0] == ""){
			json.put("status", "Error");
			json.put("message", "参数均为空！");
			content = json.toString();
			return SUCCESS;
		}
		if (((String[])parameters.get("username"))[0] == ""){
			parameters.remove("username");
		}
		if (((String[])parameters.get("sex"))[0] == ""){
			parameters.remove("sex");
		}
		if (((String[])parameters.get("mobile"))[0] == ""){
			parameters.remove("mobile");
		}
		if (parameters.get("username") != null){
			user.setUsername(((String[])parameters.get("username"))[0]);
		}
		if (parameters.get("sex") != null){
			user.setSex(Integer.parseInt(((String[])parameters.get("sex"))[0]));
		}
		if (parameters.get("mobile") != null){
			user.setMobile(Long.parseLong(((String[])parameters.get("mobile"))[0]));
		}
		userBiz.ModifyUser(parameters);
		json.put("status", "Success");
		json.put("message", "更改成功！");
		content = json.toString();
		return SUCCESS;
	}
}
