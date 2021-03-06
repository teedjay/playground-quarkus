package quarkus.upload;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/upload")
@Tag(name = "others")
@Produces(MediaType.APPLICATION_JSON)
public class UploadResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Operation(summary = "Upload 'multipart/form-data' content", description = "Usage : curl -i -F 'file=@pom.xml' -F 'json={\"id\" = \"123\"}' -F 'name=teedjay' http://localhost:8080/upload")
    public List<UploadedFile> uploadMultiPartData(MultipartFormDataInput request) {
        return request.getParts().stream().map(p -> new UploadedFile(p)).collect(Collectors.toList());
    }

    public class UploadedFile {
        public String name;
        public String mediaType;
        public String content;
        public UploadedFile(InputPart part) {
            mediaType = part.getMediaType().toString();
            try {
                content = part.getBodyAsString();
                if (content.length() > 50) content = content.substring(0, 50);
            } catch (Exception ex) {
                content = ex.getMessage();
            }
        }
    }

}
