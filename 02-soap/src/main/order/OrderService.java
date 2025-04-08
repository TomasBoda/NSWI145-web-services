package main.order;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface OrderService {

	@WebMethod
	public String placeOrder(String bookId, String customerId);
	
	@WebMethod
	public String makePayment(String orderId, double amount);
	
	@WebMethod
	public String sendConfirmation(String orderId);
}
