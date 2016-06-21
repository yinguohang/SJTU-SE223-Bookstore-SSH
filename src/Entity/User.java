package Entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;

public class User extends Object implements Comparable<User>{
	private long id;
	private String username;
	private String password;
	private Date createDate;
	private int sex;
	private double coin;
	private long mobile;
	private Set sellBooks = new HashSet();
	private int priority;
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	private Set orders = new HashSet();
	
	
	public Set getOrders() {
		return orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getSellBooks() {
		return sellBooks;
	}

	public void setSellBooks(Set sellBooks) {
		this.sellBooks = sellBooks;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public double getCoin() {
		return coin;
	}

	public void setCoin(double coin) {
		this.coin = coin;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int compareTo(User user){
		return (int)(this.getId() - user.getId());
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User user = (User)obj;
		return user.getId() ==getId()&&
				user.getUsername().equals(getUsername())&&
				user.getPassword().equals(getPassword())&&
				user.getCreateDate().equals(getCreateDate())&&
				user.getSex()==getSex()&&
				user.getCoin()==getCoin()&&
				user.getMobile()==getMobile()&&
				user.getSellBooks().equals(getSellBooks())&&		
				user.getPriority() == getPriority();		
	}
	
	public int getAllBookNumber(){
		int sum = 0;
		Iterator iter = orders.iterator();
		while (iter.hasNext()){
			sum += ((Order)iter.next()).getAllBookNumber();
		}
		return sum;
	}
}
