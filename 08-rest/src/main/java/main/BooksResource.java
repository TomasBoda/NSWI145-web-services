package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBElement;

@Path("/books")
public class BooksResource {

	private static Map<String, Book> books = new HashMap<>();

	static {
	    books.put("id001", new Book("id001", "Harry Potter", "J. K. Rowling", "2000"));
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Book> getBooks() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.addAll(books.values());
	    return bookList;

	}

	@GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("{bookId}")
    public Response getBook(@PathParam("bookId") String bookId) {
        Book book = books.get(bookId);
        
        if (book == null) {
            return Response
            	.status(Response.Status.NOT_FOUND)
            	.entity("Book with ID " + bookId + " not found.")
            	.build();
        }

        return Response
        	.status(Response.Status.OK)
        	.entity(book)
        	.build();
    }
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	@Path("{bookId}")
    public Response putBook(@PathParam("bookId") String bookId, JAXBElement<Book> bookElement) {
	    Book book = bookElement.getValue();
	    
	    
	    if (!book.getId().equals(bookId)) {
	    	return Response
	    		.status(Response.Status.CONFLICT)
	    		.entity("Book IDs from URL and request body do not match.")
	    		.build();
	    }
	    
	    if (!books.containsKey(bookId)) {
	    	return Response
	    		.status(Response.Status.NOT_FOUND)
	    		.entity("Book with ID " + bookId + " not found.")
	    		.build();
	    }

	    books.put(bookId, book);
	    
	    return Response
	    	.status(Response.Status.OK)
	    	.entity("Book updated successfully.")
	    	.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createBook(JAXBElement<Book> bookElement) {
	    Book book = bookElement.getValue();

	    if (books.containsKey(book.getId())) {
	    	return Response
	    		.status(Response.Status.CONFLICT)
	    		.entity("Book with ID " + book.getId() + " already exists.")
	    		.build();
	    }

	    books.put(book.getId(), book);

	    String location = "/books/" + book.getId();
	    return Response
	    	.created(java.net.URI.create(location))
	    	.status(Response.Status.OK)
	    	.entity("Book created successfully.")
	    	.build();
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	@Path("{bookId}")
	public Response deleteBook(@PathParam("bookId") String bookId) {
	    if (!books.containsKey(bookId)) {
	        return Response
	        	.status(Response.Status.NOT_FOUND)
	        	.entity("Book with ID " + bookId + " not found.")
	        	.build();
	    }

	    books.remove(bookId);
	    
	    return Response
	    	.status(Response.Status.OK)
	    	.entity("Book with ID " + bookId + " deleted successfully.")
	    	.build();
	}
}
