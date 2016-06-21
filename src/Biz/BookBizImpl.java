package Biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.Session;

import com.ygh.HibernateUtil;

import net.sf.json.JSONObject;
import Dao.BookDao;
import Dao.BookDaoImpl;
import Entity.Book;
import Entity.ShopCartItem;
import Entity.User;

/* BookBizImpl 书籍服务层实现，处理获取书籍数据的相关服务
 * */

public class BookBizImpl implements BookBiz{
	BookDao bookDao;
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public BookBizImpl(){
	}
	public boolean addBook(Book book){
		return bookDao.addBook(book);
	}
	public Book[] getAllBook(){
		return bookDao.getAllBook();
	}
	public ArrayList getBookDetail(HashMap hashMap){
		Iterator iter = hashMap.entrySet().iterator();
		ArrayList arrayList = new ArrayList();
		while (iter.hasNext()){
			ShopCartItem item = new ShopCartItem();
			Entry entry = (Entry)iter.next();
			item.book = bookDao.getBookById((int)entry.getKey());
			item.bookNum = (int)entry.getValue();
			arrayList.add(item);
		}
		return arrayList;
	}
	public Book[] searchBook(String content){
		return bookDao.searchBook(content);
	}
	public void modifyBook(Map<String, Object> parameters, User user){
		int bookId = Integer.parseInt(((String[])parameters.get("id"))[0]);
		Book book = bookDao.getBookById(bookId);
		book.setName(((String[])parameters.get("name"))[0]);
		book.setAuthor(((String[])parameters.get("author"))[0]);
		book.setPublisher(((String[])parameters.get("publisher"))[0]);
		book.setPrice(Double.parseDouble(((String[])parameters.get("price"))[0]));
		book.setStock(Integer.parseInt(((String[])parameters.get("stock"))[0]));
		bookDao.updateBook(book);
		Set set = user.getSellBooks();
		Iterator iter = set.iterator();
		while (iter.hasNext()){
			Book bookNow = (Book)iter.next(); 
			if (bookNow.getId() == bookId){
				bookNow.copy(book);
			}
		}
	}
	public void removeBook(int bookId){
		bookDao.removeBook(bookId);
	}
}
