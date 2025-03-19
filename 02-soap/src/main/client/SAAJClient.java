package main.client;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

public class SAAJClient {

	public static void main(String[] args) throws SOAPException {
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();

		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
		SOAPBody soapBody = soapEnvelope.getBody();

		soapEnvelope.getHeader().detachNode();
		QName name = new QName("http://tempuri.org/", "Add", "temp");
		SOAPElement soapElement = soapBody.addBodyElement(name);

		soapElement.addChildElement(new QName("http://tempuri.org/", "intA", "temp")).addTextNode("3");
		soapElement.addChildElement(new QName("http://tempuri.org/", "intB", "temp")).addTextNode("4");
		
		String endpoint = "http://www.dneonline.com/calculator.asmx";
		SOAPMessage response = soapConnection.call(soapMessage, endpoint);
		SOAPBody responseBody = response.getSOAPBody();
		
		if (responseBody.hasFault()) {
		    System.out.println(responseBody.getFault().getFaultString());
		} else {		   
			QName AddResponseName = new QName("http://tempuri.org/", "AddResponse");
			QName AddResultName = new QName("http://tempuri.org/", "AddResult");
	
			SOAPBodyElement AddResponse = (SOAPBodyElement) responseBody.getChildElements(AddResponseName).next();
			SOAPBodyElement AddResult = (SOAPBodyElement) AddResponse.getChildElements(AddResultName).next();
	
			System.out.println(AddResult.getValue());
		}

		
		soapConnection.close();
	}
}
