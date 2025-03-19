package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.Iterator;

@WebServlet("/bank-intermediary")
public class BankIntermediaryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
	private static final String BANK_SERVICE_ENDPOINT = "http://127.0.0.1:8000/bank";
	private static final String BANK_SERVICE_NAMESPACE = "http://bank.main/";
    private static final String TWEAK_NAMESPACE = "http://tweaks.com/";
    
    private String getCurrency(String currency) {
    	if (!(currency.equals("EUR") || currency.equals("USD") || currency.equals("CZK"))) {
        	return "EUR";
        }
    	
    	return currency;
    }
    
    private double getBalanceByCurrency(double balance, String currency) {
    	if (currency.equals("EUR")) {
    		return balance;
    	}
    	
    	if (currency.equals("USD")) {
    		return balance * 1.09271;
    	}
    	
    	if (currency.equals("CZK")) {
    		return balance * 25.0416;
    	}
    	
    	return balance;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SOAPMessage clientMessage = MessageFactory.newInstance().createMessage(null, request.getInputStream());
            SOAPHeader header = clientMessage.getSOAPHeader();
            
            String currency = "EUR";

            if (header != null) {
                QName tweakName = new QName(TWEAK_NAMESPACE, "tweak");
                Iterator<?> tweakHeaders = header.getChildElements(tweakName);
                
                if (tweakHeaders.hasNext()) {
                    SOAPElement tweakElement = (SOAPElement) tweakHeaders.next();
                    String currencyValue = tweakElement.getAttribute("currency");
                    currency = getCurrency(currencyValue);
                }
            }

            SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage bankResponse = connection.call(clientMessage, BANK_SERVICE_ENDPOINT);
            connection.close();

            SOAPBody responseBody = bankResponse.getSOAPBody();

            if (!responseBody.hasFault()) {
                QName getBalanceResponseName = new QName(BANK_SERVICE_NAMESPACE, "getBalanceResponse");
                
                if (responseBody.getChildElements(getBalanceResponseName).hasNext()) {
                    SOAPBodyElement getBalanceResponse = (SOAPBodyElement) responseBody.getChildElements(getBalanceResponseName).next();
                    SOAPElement returnElement = (SOAPElement) getBalanceResponse.getChildElements(new QName("return")).next();

                    SOAPElement dataElement = (SOAPElement) returnElement.getChildElements(new QName("data")).next();
                    
                    double balance = Double.parseDouble(dataElement.getValue());
                    double balanceAfterConversion = getBalanceByCurrency(balance, currency);
                    
                    dataElement.setValue(String.valueOf(balanceAfterConversion));

                    SOAPHeader responseHeader = bankResponse.getSOAPHeader();
                    
                    if (responseHeader == null) {
                        responseHeader = bankResponse.getSOAPPart().getEnvelope().addHeader();
                    }
                    
                    SOAPElement tweakedElement = responseHeader.addChildElement("tweaked", "", TWEAK_NAMESPACE);
                    tweakedElement.setValue("Currency: " + currency);

                    bankResponse.saveChanges();
                }
            }

            response.setContentType("text/xml;charset=UTF-8");
            bankResponse.writeTo(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
