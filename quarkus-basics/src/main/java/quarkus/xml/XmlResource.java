package quarkus.xml;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/xml")
public class XmlResource {

    @ConfigProperty(name = "xml.message")
    String greeting;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public XmlData hello() {
        XmlData xmlData = new XmlData();
        xmlData.uuid =  UUID.randomUUID().toString();
        xmlData.message = greeting;
        return xmlData;
    }

}
