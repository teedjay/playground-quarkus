package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class HelloResourceTest {

    @Test
    public void makeSureHelloReturnsExpectedText() {
        given()
            .when().get("/hello")
            .then()
                .statusCode(200)
                .contentType(ContentType.TEXT)
                .body(startsWith("Hello from Quarkus\nThis RequestScoopedBean bean was created @"))
        ;
    }

    @Test
    public void makeSurePostCreatesExceptionWithJsonPayload() {
        given()
            .body("nothing")
            .when().post("/hello")
            .then()
                .statusCode(500)
                .contentType(ContentType.JSON)
                .body("message", is("Failed internally : Decoding 'nothing' not implemented"))
                .body("uuid", notNullValue())
        ;
    }

}