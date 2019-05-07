package quarkus.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Set;

@Path("/users")
@RegisterRestClient
public interface UserServiceClient {

    // typesafe rest client for accessing : https://jsonplaceholder.typicode.com/

    @GET
    @Produces("application/json")
    Set<UserData> getAllUsers();

}
