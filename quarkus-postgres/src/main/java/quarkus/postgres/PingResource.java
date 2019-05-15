package quarkus.postgres;

import io.reactiverse.pgclient.PgClient;
import io.reactiverse.pgclient.PgPool;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        String connectionUri = "postgresql://admin:password@localhost:5432/teedjay";
        PgPool client = PgClient.pool(connectionUri);

        client.query("select * from users", ar -> {
            if (ar.succeeded()) {
                ar.result().forEach(u -> System.out.println("Found id : " + u.getString("id")));
            } else {
                System.out.println("Failed : " + ar.cause().getMessage());
            }
        });

        return "hello";
    }


}