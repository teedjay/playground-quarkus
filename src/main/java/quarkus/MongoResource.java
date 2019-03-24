package quarkus;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mongo")
@Produces(MediaType.APPLICATION_JSON)
public class MongoResource {

    @Inject
    MongoService mongoService;

    @GET
    @Tag(name = "mongodb")
    @Operation(summary = "List all MongoDB databases.", description = "List of database names and some other details as JSON.")
    @APIResponse(responseCode = "200",
        description = "Lists all MongoDB databases",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(type = SchemaType.OBJECT, implementation = MongoData.class))
    )
    public Response getSomeMongoData() {
        return Response.ok(mongoService.getMongoData()).build();
    }

}
