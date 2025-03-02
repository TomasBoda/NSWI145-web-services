package main.auth;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import main.Response;
import main.Session;

@WebService
public interface AuthService {

	@WebMethod
	public Response<String> signIn(
		@WebParam(name = "username") String username,
		@WebParam(name = "password") String password
	);
	
	@WebMethod
	public Response<Session> getSession(
		@WebParam(name = "token") String token
	);
}
