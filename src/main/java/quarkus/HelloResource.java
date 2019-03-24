package quarkus;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class HelloResource {

    @ConfigProperty(name = "hello.message")
    String message;

    @Inject
    RequestScoopedBean requestScoopedBean;

    @Inject
    MockableService mockableService;

    @GET
    @Operation(summary = "Returns a text from the server side.",
            description = "Text contains data from microprofile config and an injected request scooped bean."
    )
    @Counted(name = "myCustomHelloCounter", absolute = true, monotonic = true)
    public String hello() {
        return message + "\n" + requestScoopedBean.getInfoAboutWhenCreated() + "\n" + mockableService.getDescription();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(summary = "Will always generate 500 Internal Server Error.",
        description = "Returns JSON data with details about the server error."
    )
    public Response throwException(String data) {
        throw new IllegalStateException("Decoding '" + data + "' not implemented");
    }

}