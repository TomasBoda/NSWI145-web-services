package main;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import java.util.HashMap;
import java.util.Map;

public class HelloClient {
	
    public static void main(String[] args) {
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setServiceClass(HelloService.class);
        factory.setAddress("http://localhost:8080/hello?wsdl");

        HelloService client = (HelloService) factory.create();

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
        outProps.put(WSHandlerConstants.USER, "admin");
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
        outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new PasswordCallback());

        WSS4JOutInterceptor interceptor = new WSS4JOutInterceptor(outProps);
        Client cxfClient = org.apache.cxf.frontend.ClientProxy.getClient(client);
        cxfClient.getOutInterceptors().add(interceptor);
        
        cxfClient.getInInterceptors().add(new LoggingInInterceptor());
        cxfClient.getOutInterceptors().add(new LoggingOutInterceptor());

        String response = client.sayHello("John");
        System.out.println(response);
    }
}