package main;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class PasswordValidator implements CallbackHandler {

	@Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback) {
                WSPasswordCallback pc = (WSPasswordCallback) callback;

                System.out.println("Username: " + pc.getIdentifier());
                System.out.println("Password: " + pc.getPassword());
                
                if (!"admin".equals(pc.getIdentifier())) {
                	throw new SecurityException("Invalid username.");
                }
                
                if (!"password123".equals(pc.getPassword())) {
                	throw new SecurityException("Invalid password.");
                }

                return;
            }
        }
    }
}