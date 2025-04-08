package main.bookstore;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface BookStoreService {

	@WebMethod
	public String searchBook(String title);
	
	@WebMethod
	public Double getBookPrice(String bookId);
}
