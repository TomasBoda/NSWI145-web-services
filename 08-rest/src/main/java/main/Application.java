package main;

import org.glassfish.jersey.server.ResourceConfig;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class Application extends ResourceConfig {
	
    public Application() {
      	packages("main");
  	}
}
