package main;

import javax.jws.WebService;

@WebService(targetNamespace = "http://main/", endpointInterface = "main.BankService", portName = "BankServiceImplPort", serviceName = "BankServiceImplService")
public class BankServiceImpl implements BankService {

	@Override
	public double getBalance() {		
		return this.getAccountBalance();
	}

	@Override
	public double sendMoney(Double amount) {
		return this.getAccountBalance() - amount;
	}
	
	private double getAccountBalance() {
		return 2250.45;
	}
}