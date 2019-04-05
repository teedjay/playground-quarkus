package quarkus;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/secret")
@Tag(name = "jwt")
@Produces(MediaType.APPLICATION_JSON)
public class ProtectedResource {

    @Inject
    @Claim(standard = Claims.preferred_username)
    String preferred_username;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed({"offline_access"})
    public ProtectedData getSomeDataFromTheJWT() {
        ProtectedData pd = new ProtectedData();
        pd.username = preferred_username;
        pd.groups =jwt.getGroups();
        pd.claimNames = jwt.getClaimNames();
        return pd;
    }

}
