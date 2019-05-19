package quarkus.json;

import javax.json.Json;
import javax.json.JsonPatchBuilder;
import javax.json.JsonReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;

@Path("/json")
@Produces(MediaType.APPLICATION_JSON)
public class JsonPatcherResource {

    String inputJson = "{ 'id':'123', 'name':'tester' }".replace("'", "\"");

    @GET
    public String patchMyJson(String newName) {
        JsonReader reader = Json.createReader(new StringReader(inputJson));
        JsonPatchBuilder builder = Json.createPatchBuilder();
        String result = builder
                .add("/user", Json.createObjectBuilder().add("id", "17").build())
                .add("/user/name", newName)
                .add("/user/phone", "87654321")
                .remove("/name")
                .replace("/id", "321")
                .build()
                .apply(reader.readObject()).toString();
        return result;
    }


}
