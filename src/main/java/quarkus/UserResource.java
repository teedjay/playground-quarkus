package quarkus;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @ConfigProperty(name = "userservice.url")
    String userServiceURL;

    @GET
    @Tag(name = "rest-client")
    @Operation(summary = "Return a list of users.", description = "List subset of all users by calling an external REST APP.")
    public Response getExternalUsers() throws Exception {
        UserServiceClient userServiceClient = RestClientBuilder.newBuilder().baseUrl(new URL(userServiceURL)).build(UserServiceClient.class);
        return Response.ok(userServiceClient.getAllUsers()).build();
    }

}
