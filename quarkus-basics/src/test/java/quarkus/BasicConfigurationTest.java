package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class BasicConfigurationTest {

    @Inject
    HelloResource helloResource;

    @Test
    public void makeSureDefaultConfigHasBeenInjected() {
        assertEquals("Hello from Quarkus", helloResource.message);
    }

}
