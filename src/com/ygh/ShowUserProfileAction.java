package com.ygh;

import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import Biz.UserBiz;
import Entity.User;
import Entity.UserProfile;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* ShowUserProfileAction 显示用户个性化信息
 * */

public class ShowUserProfileAction extends ActionSupport{
	private UserBiz userBiz;
	private Map<String, String> map;
	public UserBiz getUserBiz() {
		return userBiz;
	}
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public String execute() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		Map<String, Object> hSession = context.getSession();
		User user = (User)hSession.get("user");
		UserProfile userProfile = userBiz.getUserProfileByMysqlId(user.getId());
		JSONObject json = JSONObject.fromObject(userProfile.getProfile());
		map = (Map)json;
		return "continue";
	}
}
