package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import Entity.Book;
import Entity.ShopCartItem;
import Entity.User;

import com.ygh.HibernateUtil;

/* BookDaoImpl 书籍持久化操作类实现
 * */

public class BookDaoImpl implements BookDao{
	@Override
	public boolean addBook(Book book){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		return true;
	}
	
	public Book[] getAllBook(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Book");
		List list = query.list();
		Book[] bookArray = new Book[list.size()];
		list.toArray(bookArray);
		session.getTransaction().commit();
		return bookArray;
	}
	public Book[] searchBook(String content){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "from Book where name like ? or publisher like ? or author like ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, '%' + content + '%', StandardBasicTypes.STRING);
		query.setParameter(1, '%' + content + '%', StandardBasicTypes.STRING);
		query.setParameter(2, '%' + content + '%', StandardBasicTypes.STRING);
		List list = query.list();
		Book[] bookArray = new Book[list.size()];
		list.toArray(bookArray);
		session.getTransaction().commit();
		return bookArray;
	}
	public void removeBook(int bookId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("delete Book as book where book.id=?");
		query.setParameter(0, bookId, StandardBasicTypes.INTEGER);
		query.executeUpdate();
		session.getTransaction().commit();
	}
	public Book getBookById(int bookId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Book book = (Book)session.get(Book.class, bookId);
		session.getTransaction().commit();
		return book;
	}
	public void updateBook(Book book){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(book);
		session.getTransaction().commit();
	}
}