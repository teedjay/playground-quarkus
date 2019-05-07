package quarkus.panache;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private static int PAGE_SIZE = 5;

    @GET
    public List<User> getAllUsers() {
        return User.listAll();
    }

    @GET
    @Path("{id}")
    public User getUserById(@PathParam("id") Long id) {
        return User.findById(id);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewUser(User user, @Context UriInfo uriInfo) {
        user.id = null; // ignore any id specified by user
        user.persist();
        URI createdResource = uriInfo.getAbsolutePathBuilder().path("" + user.id).build();
        return Response.created(createdResource).build();
    }

    @GET
    @Path("page/{id}")
    public Response getPageOfUsers(@PathParam("id") int pageNumber) {
        PanacheQuery<User> pagedUsers = User.findAll().page(pageNumber, PAGE_SIZE);
        List<User> users = pagedUsers.list();
        int numberOfPages = pagedUsers.pageCount();
        return Response.ok(users).header("number-of-pages", numberOfPages).build();
    }

}
