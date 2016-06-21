package Entity;

import java.util.Date;

public class Book {
	private int id;
	private String name;
	private String author;
	private String publisher;
	private double price;
	private int stock;
	private Date createDate;
	private User seller;
	private int type;
	
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock){
		this.stock = stock;
	}
	public void copy(Book book){
		name = book.getName();
		author = book.getAuthor();
		publisher = book.getPublisher();
		price = book.getPrice();
		stock = book.getStock();
	}
}
