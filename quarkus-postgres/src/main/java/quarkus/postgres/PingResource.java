package quarkus.postgres;

import io.reactiverse.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
public class PingResource {

    @Inject
    PgPool client;

    @Inject
    PostgresPubSub postgresPubSub;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("list")
    public void listSomeUsers() {
        client.query("select * from users", ar -> {
            if (ar.succeeded()) {
                ar.result().forEach(u -> System.out.println("Found id : " + u.getString("id")));
            } else {
                System.out.println("Failed : " + ar.cause().getMessage());
            }
        });
    }

    @GET
    @Path("send/{message}")
    public void publishMessage(@PathParam("message") String message) {
        postgresPubSub.sendNotification(message);
    }

}