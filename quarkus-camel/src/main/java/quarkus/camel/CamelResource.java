package quarkus.camel;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/camel")
@Produces(MediaType.APPLICATION_JSON)
public class CamelResource {

    @Inject
    DataHolder dataHolder;

    @GET
    public List<Store> listStores() {
        return dataHolder.stores;
    }

    @GET
    @Path("category")
    public List<Store> listStoresByCategory() {
        return dataHolder.stores.stream().sorted(Comparator.comparing(Store::getCategory)).collect(Collectors.toList());
    }

    @GET
    @Path("{number}")
    public Store getStoreNumber(@PathParam("number") String number) {
        return dataHolder.stores.stream().filter(s -> number.equals(s.number)).findFirst().orElse(null);
    }

}