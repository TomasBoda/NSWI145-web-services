package main.bookstore;

import javax.jws.WebService;
import javax.jws.WebMethod;

import java.util.ArrayList;
import java.util.HashMap;

@WebService
public class BookStoreServiceImpl implements BookStoreService {
	
	static class Book {
		
		public String id;
		public String title;
		public double price;
		
		public Book(String id, String title, double price) {
			this.id = id;
			this.title = title;
			this.price = price;
		}
	}

	private ArrayList<Book> books;
	
	public BookStoreServiceImpl() {
		books = new ArrayList<Book>();
		
		books.add(new Book("id001", "Harry Potter", 19.99));
		books.add(new Book("id002", "Hunger Games", 14.99));
		books.add(new Book("id003", "Lord of the Rings", 24.99));
	}
	
	@Override
	@WebMethod
	public String searchBook(String title) {
		for (Book book : books) {
			if (book.title.equals(title)) {
				return book.id;
			}
		}
		
		return null;
	}

	@Override
	@WebMethod
	public Double getBookPrice(String bookId) {
		for (Book book : books) {
			if (book.id.equals(bookId)) {
				return book.price;
			}
		}
		
		return null;
	}
}
