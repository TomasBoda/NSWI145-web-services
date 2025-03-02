package main;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Session {
	
	private String token;
	private User user;
	
	public Session() {}
	
	public Session(String token, User user) {
		this.token = token;
		this.user = user;
	}
	
	@XmlElement
	public String getToken() {
		return this.token;
	}
	
	@XmlElement
	public User getUser() {
		return this.user;
	}
}
