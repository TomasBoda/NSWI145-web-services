package main;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
		
	private String username;
	
	public User() {}
	
	public User(String username) {
		this.username = username;
	}
	
	@XmlElement
	public String getUsername() {
		return this.username;
	}
}
