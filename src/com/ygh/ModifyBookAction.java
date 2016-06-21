package com.ygh;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Biz.BookBiz;
import Biz.UserBiz;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* ModifyBookAction 修改书籍信息
 * */

public class ModifyBookAction extends ActionSupport{
	private String content;
	private BookBiz bookBiz;
	public BookBiz getBookBiz() {
		return bookBiz;
	}
	public void setBookBiz(BookBiz bookBiz) {
		this.bookBiz = bookBiz;
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
		User user = (User)hSession.get("user");
		if (parameters.get("id") == null ||
				parameters.get("author") == null ||
				parameters.get("publisher") == null ||
				parameters.get("price") == null ||
				parameters.get("stock") == null){
			json.put("status", "Error");
			json.put("status", "错误的参数!");
			content = json.toString();
			return SUCCESS;
		}
		bookBiz.modifyBook(parameters, user);
		hSession.put("user", user);
		json.put("status", "Success");
		json.put("message", "更改成功！");
		content = json.toString();
		return SUCCESS;
	}
}
