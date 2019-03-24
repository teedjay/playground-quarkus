package quarkus;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class SwaggerUITest {

    @TestHTTPResource("index.html")
    URL url;

    @Test
    public void makeSureIndexHtmlIsSwaggerUI() throws Exception {
        try (InputStream in = url.openStream()) {
            String contents = new String(in.readAllBytes());
            assertTrue(contents.contains("<title>Swagger UI</title>"));
        }
    }

}
