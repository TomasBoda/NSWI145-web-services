package main;

import javax.xml.ws.Endpoint;
import main.auth.AuthServiceImpl;
import main.bank.BankServiceImpl;
import main.bookstore.BookStoreServiceImpl;
import main.order.OrderServiceImpl;

public class Main {
	
	private String baseUrl = "http://127.0.0.1:8000";
	
	public Main() {
		AuthServiceImpl authService = new AuthServiceImpl();
		BankServiceImpl bankService = new BankServiceImpl(authService);
		this.publish("AuthService", "/auth", authService);
		this.publish("BankService", "/bank", bankService);
		
		//BookStoreServiceImpl bookStoreService = new BookStoreServiceImpl();
		//OrderServiceImpl orderService = new OrderServiceImpl();
		//this.publish("BookStoreService", "/bookstore", bookStoreService);
		//this.publish("OrderService", "/order", orderService);
	}
	
	private void publish(String name, String url, Object implementator) {
		Endpoint.publish(this.baseUrl + url, implementator);
		System.out.println(name + " started successfully at " + this.baseUrl + url);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
