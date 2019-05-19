package quarkus.async;

import io.vertx.axle.core.eventbus.EventBus;
import io.vertx.axle.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@Path("/event")
@Tag(name = "async")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class EventResource {

    // ab -c 25 -n 100 http://localhost:8080/event/teedjay

    @Inject
    EventBus bus;

    @GET
    @Path("/{name}")
    public CompletionStage<JsonObject> convertName(@PathParam("name") String name) {
        String message = String.format("Hello from '%s' on thread '%s'", name, Thread.currentThread().getName());
        return bus.<JsonObject>send("convert", message).thenApply(Message::body);
    }

}
