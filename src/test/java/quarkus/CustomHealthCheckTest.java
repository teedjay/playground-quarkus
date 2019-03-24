package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CustomHealthCheckTest {

    @Test
    public void makeSureOurCustomHealthCanBeRead() {
        given()
            .when().get("/health")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("checks.data.storage[0]", is("10gb"))
        ;
    }

}