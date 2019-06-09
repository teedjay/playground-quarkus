package quarkus.camel;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CamelResourceTest {

    @Test
    public void testCamelEndpoint() {
        given()
            .when().get("/camel")
            .then()
                .statusCode(200)
            ;
    }

}