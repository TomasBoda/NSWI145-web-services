package main;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "BankService", targetNamespace = "http://main/")
public interface BankService {

	@WebMethod(operationName = "getBalance", action = "urn:GetBalance")
	public double getBalance();
}