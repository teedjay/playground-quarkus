package quarkus.undertow;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/undertow")
@Produces(MediaType.TEXT_PLAIN)
public class UndertowResource {

    @ConfigProperty
    String greeting;

    @GET
    public String doStuff() {
        return greeting;
    }

}