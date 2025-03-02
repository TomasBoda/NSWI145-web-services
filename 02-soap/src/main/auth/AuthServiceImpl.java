package main.auth;

import javax.jws.WebService;
import javax.jws.WebMethod;

import main.Response;
import main.Session;
import main.User;

@WebService
public class AuthServiceImpl implements AuthService {

	private Session session;
	
	@WebMethod
	@Override
	public Response<String> signIn(String username, String password) {
		if (!this.validate(username, password)) {
			return new Response<String>(401, "Invalid credentials");
		}
		
		String token = this.generateToken();
		this.session = new Session(token, new User(username));
		
		return new Response<String>(200, "User signed in successfully", token);
	}
	
	@WebMethod
	@Override
	public Response<Session> getSession(String token) {
		if (this.session == null) {
			return new Response<Session>(404, "Session not found");
		}
		
		if (!this.session.getToken().equals(token)) {
			return new Response<Session>(404, "Session not found");
		}
		
		return new Response<Session>(200, "Session retrieved successfully", this.session);
	}
	
	private boolean validate(String username, String password) {
		return username.equals("username") && password.equals("password");
	}
	
	private String generateToken() {
		return "123456789";
	}
}
