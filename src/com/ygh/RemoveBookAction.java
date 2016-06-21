package com.ygh;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Biz.BookBiz;
import Entity.Book;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* 删除书籍操作
 * */

public class RemoveBookAction extends ActionSupport{
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
	public String execute() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		Map<String, Object> hSession = context.getSession();
		JSONObject json = new JSONObject();
		if (parameters.get("bookId") == null){
			json.put("status", "Error");
			json.put("message", "错误的参数!");
			content = json.toString();
			return SUCCESS;
		}
		int bookId = Integer.parseInt(((String[])parameters.get("bookId"))[0]);
		System.out.println(bookId);
		bookBiz.removeBook(bookId);
		User user = (User)hSession.get("user");
		Set books = user.getSellBooks();
		Iterator iter = books.iterator();
		while (iter.hasNext()){
			Book book = (Book)iter.next();
			if (book.getId() == bookId){
				iter.remove();
				break;
			}
		}
		json.put("status", "Success");
		json.put("message", "删除成功！");
		content = json.toString();
		return SUCCESS;
	}
}
