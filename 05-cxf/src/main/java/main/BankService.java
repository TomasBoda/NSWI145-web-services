package main;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "BankService", targetNamespace = "http://main/")
public interface BankService {

	@WebMethod(operationName = "getBalance", action = "urn:GetBalance")
	public double getBalance();
	
	@WebMethod(operationName = "sendMoney", action = "urn:SendMoney")
	public double sendMoney(Double amount);
}