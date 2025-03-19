package main.auth;

import javax.jws.WebService;
import javax.jws.WebMethod;

import main.Response;
import main.Session;

@WebService
public interface AuthService {

	@WebMethod
	public Response<String> signIn(String username, String password);
	
	@WebMethod
	public Response<Session> getSession(String token);
}
