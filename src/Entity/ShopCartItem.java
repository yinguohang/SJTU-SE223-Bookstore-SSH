package Entity;


public class ShopCartItem {
	public Book book;
	public int bookNum;
	public synchronized Book getBook() {
		return book;
	}
	public synchronized void setBook(Book book) {
		this.book = book;
	}
	public synchronized int getBookNum() {
		return bookNum;
	}
	public synchronized void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
}
