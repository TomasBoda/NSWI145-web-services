package main;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface HelloService {

	@WebMethod
	public String sayHello(String name);
}