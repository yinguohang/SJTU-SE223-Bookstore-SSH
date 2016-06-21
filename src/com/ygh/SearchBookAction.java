package com.ygh;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Biz.BookBiz;
import Entity.Book;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* SearchBookAction 搜索书籍操作
 * */

public class SearchBookAction extends ActionSupport{
	private String content;
	private Book[] bookArray;
	private BookBiz bookBiz;
	public BookBiz getBookBiz() {
		return bookBiz;
	}
	public void setBookBiz(BookBiz bookBiz) {
		this.bookBiz = bookBiz;
	}
	public Book[] getBookArray() {
		return bookArray;
	}
	public void setBookArray(Book[] bookArray) {
		this.bookArray = bookArray;
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
		if (parameters.get("content") == null){
			content = "错误的参数！";
			return "finish";
		}
		String searchContent = ((String[])parameters.get("content"))[0];
		bookArray = bookBiz.searchBook(searchContent);
		return "continue";
	}
}
