package com.ygh;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.hibernate.Session;

import Entity.Book;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* DisplayBookAction 展示用户卖的书籍
 * */

public class DisplayBookAction extends ActionSupport{
	private String content;
	private Book[] bookArray;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Book[] getBookArray() {
		return bookArray;
	}

	public void setBookArray(Book[] bookArray) {
		this.bookArray = bookArray;
	}

	public String execute() throws Exception {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> hSession = context.getSession();
		JSONObject json = new JSONObject();
		User user = (User)hSession.get("user");
		Set bookSet = user.getSellBooks();
		bookArray = new Book[bookSet.size()];
		bookSet.toArray(bookArray);
		return "continue";
	}
}
