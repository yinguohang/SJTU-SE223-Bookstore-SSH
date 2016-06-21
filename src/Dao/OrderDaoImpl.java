package Dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import Entity.Book;
import Entity.Order;
import Entity.User;

import com.ygh.HibernateUtil;

/* OrderDaoImpl 订单持久化操作类实现
 * */

public class OrderDaoImpl implements OrderDao{
	public boolean createOrderFromShopCart(HashMap shopCart, User user, JSONObject json){
		Order order = new Order();
		order.setCreateDate(new Date());
		Iterator iter = shopCart.entrySet().iterator();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		double price = 0;
		HashMap<Book, Integer> hashMap = new HashMap();
		while (iter.hasNext()){
			Entry entry = (Entry)iter.next();
			Book book = (Book)session.get(Book.class, (int)entry.getKey());
			int bookNum = (int)entry.getValue();
			if (book.getSeller().getId() == user.getId()){
				session.getTransaction().commit();
				json.put("status", "Error");
				json.put("message", "对不起，您不能买自己的书！");
				return false;
			}
			if (book.getStock() < bookNum){
				session.getTransaction().commit();
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
			session.getTransaction().commit();
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
		session.save(order);
		session.getTransaction().commit();
		json.put("status", "Success");
		json.put("message", "恭喜您下单成功!");
		return true;
	}
	public Order[] getOrderByDate(Date startDate, Date endDate){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		String hql = "from Order where createDate <= ? and createDate >= ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, endDate,StandardBasicTypes.DATE);
		query.setParameter(1, startDate,StandardBasicTypes.DATE);
		List<User> list = query.list();
		Order[] orders = new Order[list.size()];
		System.out.println(list.size());
		list.toArray(orders);
		session.getTransaction().commit();
		return orders;
	}	
	public void addOrder(Order order){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(order);
		session.getTransaction().commit();
	}
}
