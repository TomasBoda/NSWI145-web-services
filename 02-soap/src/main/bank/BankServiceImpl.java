package main.bank;

import javax.jws.WebService;
import javax.jws.WebMethod;

import main.Response;
import main.Session;
import main.auth.AuthServiceImpl;

@WebService
public class BankServiceImpl implements BankService {
	
	private AuthServiceImpl authService;
	
	public BankServiceImpl(AuthServiceImpl authService) {
		this.authService = authService;
	}

	@Override
	@WebMethod
	public Response<Double> getBalance(String token) {
		Response<Session> response = this.authService.getSession(token);
		
		if (response.getStatus() != 200) {
			return new Response<Double>(response.getStatus(), response.getMessage());
		}
		
		double balance = this.getAccountBalance();
		return new Response<Double>(200, "Balance retrieved successfully", balance);
	}

	@Override
	@WebMethod
	public Response<Double> sendMoney(String token, Double amount) {
		Response<Session> response = this.authService.getSession(token);
		
		if (response.getStatus() != 200) {
			return new Response<Double>(response.getStatus(), response.getMessage());
		}
		
		double newBalance = this.getAccountBalance() - amount;
		return new Response<Double>(200, "Money sent successfully", newBalance);
	}
	
	private double getAccountBalance() {
		return 2250.45;
	}
}
