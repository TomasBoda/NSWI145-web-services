package main.bank;

import javax.jws.WebService;
import javax.jws.WebMethod;

import main.Response;

@WebService
public interface BankService {

	@WebMethod
	public Response<Double> getBalance(String token);
	
	@WebMethod
	public Response<Double> sendMoney(String token, Double amount);
}
