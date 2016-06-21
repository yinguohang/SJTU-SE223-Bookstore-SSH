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

/* ShowAllUserAction 显示所有用户操作
 * */

public class ShowAllUserAction extends ActionSupport{
	private String content;	
	User[] userArray;
	int[] orderNum;
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
	public User[] getUserArray() {
		return userArray;
	}
	public void setUserArray(User[] userArray) {
		this.userArray = userArray;
	}
	public int[] getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int[] orderNum) {
		this.orderNum = orderNum;
	}
	public String execute() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> hSession = context.getSession();
		JSONObject json = new JSONObject();
		Map<String, Object> ans = userBiz.getAllUser();
		userArray = (User[])ans.get("userArray");
		orderNum = (int[])ans.get("orderNum");
		return SUCCESS;
	}
}
