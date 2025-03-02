package main;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({Session.class, User.class, String.class, Integer.class, Boolean.class})
public class Response<T> {

	private int status;
	private String message;
	private T data;
	
	public Response() {}
	
	public Response(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public Response(int status, String message) {
		this.status = status;
		this.message = message;
		this.data = null;
	}
	
	@XmlElement
	public int getStatus() {
		return status;
	}
	
	@XmlElement
	public String getMessage() {
		return message;
	}
	
	@XmlElement
	public T getData() {
		return data;
	}
}
