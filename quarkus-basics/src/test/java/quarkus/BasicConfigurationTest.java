package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import quarkus.mongo.MongoService;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BasicConfigurationTest {

    @Inject
    HelloResource helloResource;

    @Inject
    MongoService mongoService;

    @Test
    public void makeSureDefaultConfigHasBeenInjected() {
        assertEquals("Hello from Quarkus", helloResource.message);
        assertEquals("mongodb://localhost:27017", mongoService.getMongodbUrl());
    }

}
