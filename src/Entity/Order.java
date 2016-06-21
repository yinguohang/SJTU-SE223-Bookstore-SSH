package Entity;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Order implements Comparable<Order>{
	private int id;
	private User owner;
	private double price;
	private Map<Book, Integer> books;
	private Date createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Map<Book, Integer> getBooks() {
		return books;
	}
	public void setBooks(Map<Book, Integer> books) {
		this.books = books;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getAllBookNumber(){
		int sum = 0;
		Iterator<Entry<Book, Integer>> iter = books.entrySet().iterator();
		while (iter.hasNext()){
			sum += iter.next().getValue();
		}
		return sum;
	}
	
	@Override
	public int compareTo(Order order){
		return -(int)(this.getId() - order.getId());
	}
	
}
