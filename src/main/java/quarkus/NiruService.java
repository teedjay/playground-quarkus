package quarkus;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/niru")
public class NiruService {

    @ConfigProperty(name = "niru.text")
    String greeting;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonData hello() {
        JsonData jsonData = new JsonData();
        jsonData.uuid =  UUID.randomUUID().toString();
        jsonData.text = greeting;
        return jsonData;
    }

    public class JsonData {
        public String uuid;
        public String text;
    }

}
