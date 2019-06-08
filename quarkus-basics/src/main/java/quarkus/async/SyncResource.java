package quarkus.async;

import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sync")
@Tag(name = "async")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class SyncResource {

    // ab -c 25 -n 100 http://localhost:8080/sync/teedjay

    @Inject
    ConverterService service;

    @GET
    @Path("{name}")
    public JsonObject convertName(@PathParam("name") String name) {
        String message = String.format("Hello from '%s' on thread '%s'", name, Thread.currentThread().getName());
        return service.convertToJson(message);
    }

}
