package main.order;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class OrderServiceImpl implements OrderService {

	@Override
	@WebMethod
	public String placeOrder(String bookId, String customerId) {
		String orderId = "order001";
		return orderId;
	}

	@Override
	@WebMethod
	public String makePayment(String orderId, double amount) {
		String status = "success";
		return status;
	}

	@Override
	@WebMethod
	public String sendConfirmation(String orderId) {
		String message = "Book ordered successfully";
		return message;
	}

}
