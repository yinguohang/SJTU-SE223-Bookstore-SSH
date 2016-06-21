package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import Entity.Book;
import Entity.User;

/* BookDao 书籍持久化操作类
 * */

public interface BookDao {
	//添加书籍
	public boolean addBook(Book book);
	//获取所有书
	public Book[] getAllBook();
	//搜索书籍
	public Book[] searchBook(String content);
	//删除书籍
	public void removeBook(int bookId);
	//查询书籍(通过ID)
	public Book getBookById(int bookId);
	//更新书籍
	public void updateBook(Book book);
}
