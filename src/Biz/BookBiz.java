package Biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import Entity.Book;
import Entity.User;

/* BookBiz 书籍服务层，处理获取书籍数据的相关服务
 * */


public interface BookBiz {
	//添加书籍
	public boolean addBook(Book book);
	//获取所有书籍
	public Book[] getAllBook();
	//根据hashMapde的bookId获取book对象, 并返回ShopCartItem对象的ArrayList
	public ArrayList getBookDetail(HashMap hashMap);
	public Book[] searchBook(String content);
	//更改书籍信息：parameters->需要更新的数据; user->需要将user中的book更新
	public void modifyBook(Map<String, Object> parameters, User user);
	//删除书籍
	public void removeBook(int bookId);
}
