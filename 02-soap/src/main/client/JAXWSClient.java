package main.client;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import main.auth.AuthService;

public class JAXWSClient {
	
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://127.0.0.1:8000/auth?wsdl");
		QName qname = new QName("http://auth.main/", "AuthServiceImplService");
		
		Service service = Service.create(url, qname);
		AuthService authService = service.getPort(new QName("http://auth.main/", "AuthServiceImplPort"), AuthService.class);
		
		System.out.println(authService.signIn("username", "password"));
	}
}
