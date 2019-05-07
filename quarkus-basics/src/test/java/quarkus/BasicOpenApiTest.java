package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class BasicOpenApiTest {

    @Test
    public void makeSureWeGetYamlOpenApiDefinition() {
        given()
            .when().get("/openapi")
            .then()
                .statusCode(200)
                .contentType("application/yaml")
                .body(startsWith("---\nopenapi: 3.0.1"))
        ;
    }

}
