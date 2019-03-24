package quarkus;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllExceptionsMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        ExceptionData exceptionData = new ExceptionData();
        exceptionData.message = "Failed internally : " + e.getMessage();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON_TYPE).entity(exceptionData).build();
    }

}
