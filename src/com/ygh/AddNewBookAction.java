package com.ygh;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Biz.BookBiz;
import Biz.BookBizImpl;
import Biz.UserBiz;
import Entity.Book;
import Entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/* AddNewBookAction 添加一本新的书籍操作
 * */

public class AddNewBookAction extends ActionSupport{
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
		if (parameters.get("name") == null ||
				parameters.get("author") == null ||
				parameters.get("publisher") == null ||
				parameters.get("price") == null ||
				parameters.get("stock") == null ||
				parameters.get("type") == null){
			content = "信息不完整！";
			return "finish";
		}
		if (((String[])parameters.get("name"))[0] == "" ||
				((String[])parameters.get("author"))[0] == "" ||
				((String[])parameters.get("publisher"))[0] == "" ||
				((String[])parameters.get("price"))[0] == "" ||
				((String[])parameters.get("stock"))[0] == "" ||
				((String[])parameters.get("type"))[0] == ""){
			content = "信息不完整！";
			return "finish";
		}
		String name = ((String[])parameters.get("name"))[0];
		String author = ((String[])parameters.get("author"))[0];
		String publisher = ((String[])parameters.get("publisher"))[0];
		int type = Integer.parseInt(((String[])parameters.get("type"))[0]);
		double price = Double.parseDouble(((String[])parameters.get("price"))[0]);
		int stock = Integer.parseInt(((String[])parameters.get("stock"))[0]);
		Date date = new Date();
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPrice(price);
		book.setStock(stock);
		book.setCreateDate(date);
		book.setType(type);
		User user = (User)hSession.get("user");
		book.setSeller(user);
		bookBiz.addBook(book);
		json.put("status", "Success");
		json.put("status", "添加成功!");
		content = json.toString();
		return "continue";
	}
}
