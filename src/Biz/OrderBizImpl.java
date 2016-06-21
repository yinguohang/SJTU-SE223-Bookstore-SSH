package Biz;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.hibernate.Session;

import Dao.BookDao;
import Dao.OrderDao;
import Entity.Book;
import Entity.Order;
import Entity.User;

import com.ygh.HibernateUtil;

/* OrderBizImpl 订单服务层实现，处理获取订单数据的相关服务
 * */

public class OrderBizImpl implements OrderBiz{
	OrderDao orderDao;
	BookDao bookDao;
	public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public OrderBizImpl(){
	}
	public boolean createOrderFromShopCart(HashMap shopCart, User user, JSONObject json){
		Order order = new Order();
		order.setCreateDate(new Date());
		Iterator iter = shopCart.entrySet().iterator();
		double price = 0;
		HashMap<Book, Integer> hashMap = new HashMap();
		while (iter.hasNext()){
			Entry entry = (Entry)iter.next();
			Book book = bookDao.getBookById((int)entry.getKey());
			int bookNum = (int)entry.getValue();
			if (book.getSeller().getId() == user.getId()){
				json.put("status", "Error");
				json.put("message", "对不起，您不能买自己的书！");
				return false;
			}
			if (book.getStock() < bookNum){
				json.put("status", "Error");
				json.put("message", "对不起, "+book.getName()+"货存不足, 无法下单!");
				return false;
			}else{
				book.setStock(book.getStock() - bookNum);
			}
			price = price + book.getPrice()*bookNum;
			hashMap.put(book, bookNum);
		}
		if (price > user.getCoin()){
			json.put("status", "Error");
			json.put("message", "对不起, 您的余额不足,请联系管理员!(需要"+price+",而您仅有"+user.getCoin()+")");
			return false;
		}else{
			user.setCoin(user.getCoin() - price);
		}
		Iterator iter2 = hashMap.entrySet().iterator();
		while (iter2.hasNext()){
			Entry entry = (Entry)iter2.next();
			Book book = (Book)entry.getKey();
			book.getSeller().setCoin(book.getSeller().getCoin() + book.getPrice() * ((Integer)entry.getValue()) );
		}
		order.setPrice(price);
		order.setBooks(hashMap);
		user.getOrders().add(order);
		order.setOwner(user);
		orderDao.addOrder(order);
		json.put("status", "Success");
		json.put("message", "恭喜您下单成功!");
		return true;
	}
	public Order[] getOrderByDate(Date startDate, Date endDate){
		return orderDao.getOrderByDate(startDate, endDate);
	}
}
