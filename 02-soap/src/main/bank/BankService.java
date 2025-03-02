package main.bank;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import main.Response;

@WebService
public interface BankService {

	@WebMethod
	public Response<Double> getBalance(
		@WebParam(name = "token") String token
	);
	
	@WebMethod
	public Response<Double> sendMoney(
		@WebParam(name = "token") String token,
		@WebParam(name = "amount") Double amount
	);
}
