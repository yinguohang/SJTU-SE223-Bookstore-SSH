package com.ygh;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Biz.BookBiz;
import Biz.BookBizImpl;
import Entity.Book;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* DisplayAllBookAction 展示全部书籍
 * */

public class DisplayAllBookAction extends ActionSupport{
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
	public String execute() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		bookArray = bookBiz.getAllBook();
		return SUCCESS;
	}
}
