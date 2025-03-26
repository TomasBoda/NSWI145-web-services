package main;

import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

public class HelloServicePublisher {

	public static void main(String[] args) {
		ServerFactoryBean factory = new ServerFactoryBean();
        factory.setServiceClass(HelloServiceImpl.class);
        factory.setAddress("http://localhost:8080/hello?wsdl");

        Map<String, Object> inProps = new HashMap<>();
        inProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        inProps.put("passwordCallbackClass", PasswordValidator.class.getName());

        WSS4JInInterceptor interceptor = new WSS4JInInterceptor(inProps);
        factory.getInInterceptors().add(interceptor);

        factory.create();
        System.out.println("Service is running at http://localhost:8080/hello?wsdl");
    }
}