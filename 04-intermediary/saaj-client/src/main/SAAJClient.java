package main;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

public class SAAJClient {
	
	private static final String ENDPOINT = "http://127.0.0.1:8000/auth/";
	private static final String NAMESPACE = "http://auth.main/";
	
	private static SOAPConnection createConnection() throws SOAPException {
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		return soapConnection;
	}
	
	private static SOAPMessage createMessage(String username, String password) throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
		SOAPBody soapBody = soapEnvelope.getBody();

		soapEnvelope.getHeader().detachNode();
		QName name = new QName(NAMESPACE, "signIn", "auth");
		SOAPElement soapElement = soapBody.addBodyElement(name);

		soapElement.addChildElement(new QName("arg0")).addTextNode(username);
		soapElement.addChildElement(new QName("arg1")).addTextNode(password);
		
		soapMessage.saveChanges();
		
		return soapMessage;
	}
	
	private static void getAndPrintResponse(SOAPConnection soapConnection, SOAPMessage soapMessage) throws SOAPException {
		SOAPMessage response = soapConnection.call(soapMessage, ENDPOINT);
		SOAPBody responseBody = response.getSOAPBody();
		
		if (responseBody.hasFault()) {
		    System.out.println(responseBody.getFault().getFaultString());
		} else {		   
			QName signInResponseQName = new QName(NAMESPACE, "signInResponse");
			SOAPBodyElement signInResponse = (SOAPBodyElement) responseBody.getChildElements(signInResponseQName).next();
			
			SOAPElement returnElement = (SOAPElement) signInResponse.getChildElements(new QName("return")).next();
			
			String data = returnElement.getChildElements(new QName("data")).hasNext()
				? ((SOAPElement) returnElement.getChildElements(new QName("data")).next()).getValue()
				: "No Data";
			
			String message = returnElement.getChildElements(new QName("message")).hasNext()
				? ((SOAPElement) returnElement.getChildElements(new QName("message")).next()).getValue()
				: "No Message";
			
			String status = returnElement.getChildElements(new QName("status")).hasNext()
				? ((SOAPElement) returnElement.getChildElements(new QName("status")).next()).getValue()
				: "No Status";
			
			System.out.println("Status  | " + status);
			System.out.println("Message | " + message);
			System.out.println("Data    | " + data);
		}
	}

	public static void main(String[] args) throws SOAPException, IOException {
		SOAPConnection connection = createConnection();
		
		SOAPMessage message1 = createMessage("wrong", "wrong");
		System.out.println("Response 1 (wrong credentials)");
		System.out.println("------------------------------");
		getAndPrintResponse(connection, message1);
		
		System.out.println();
		
		System.out.println("Response 2 (correct credentials)");
		System.out.println("--------------------------------");
		SOAPMessage message2 = createMessage("username", "password");
		getAndPrintResponse(connection, message2);

		connection.close();
	}
}
