package main;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {

	private String id;
	private String title;
	private String author;
	private String published;
	
	public Book() {
		
	}
	
	public Book(String id, String title, String author, String published) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.published = published;
	}
	
	@XmlElement(name="id")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement(name="title")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement(name="author")
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@XmlElement(name="published")
	public String getPublished() {
		return this.published;
	}
	
	public void setPublished(String published) {
		this.published = published;
	}
}
