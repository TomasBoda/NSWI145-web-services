package main;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class ClientPasswordCallback implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    	for (Callback callback : callbacks) {
    		if (callback instanceof WSPasswordCallback) {
    			WSPasswordCallback pc = (WSPasswordCallback) callback;
                
                if ("username".equals(pc.getIdentifier())) {
                    pc.setPassword("password");
                }
            }
        }
    }
}