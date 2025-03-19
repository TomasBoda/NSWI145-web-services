package main;

import javax.xml.ws.Endpoint;
import main.auth.AuthServiceImpl;
import main.bank.BankServiceImpl;

public class Main {
	
	private String baseUrl = "http://127.0.0.1:8000";
	
	public Main() {
		AuthServiceImpl authService = new AuthServiceImpl();
		BankServiceImpl bankService = new BankServiceImpl(authService);
		
		this.publish("AuthService", "/auth", authService);
		this.publish("BankService", "/bank", bankService);
	}
	
	private void publish(String name, String url, Object implementator) {
		Endpoint.publish(this.baseUrl + url, implementator);
		System.out.println(name + " started successfully at " + this.baseUrl + url);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
