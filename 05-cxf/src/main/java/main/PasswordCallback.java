package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

public class PasswordCallback implements CallbackHandler {

	private Map<String, String> passwords = new HashMap<String, String>();
	
	public PasswordCallback() {
		passwords.put("username", "password");
	}

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
			
			String password = passwords.get(pc.getIdentifier());
			
			if (password != null) {
				pc.setPassword(password);
				return;
			}
		}
	}
}
