package quarkus.undertow;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UndertowResourceTest {

    @Test
    public void testUndertowEndpoint() {
        given()
            .when().get("/undertow")
            .then()
                .statusCode(200)
                .body(is("Testing Undertow"))
            ;
    }

    @Test
    public void testMongoProxy() {
        given()
            .when().get("/mongo")
            .then()
                .statusCode(200)
                .body(containsString("It looks like you are trying to access MongoDB over HTTP on the native driver port."))
        ;
    }

    @Test
    public void testSomeRandomUrl() {
        given()
                .when().get("/this/does/not/exist")
                .then()
                .statusCode(200)
                .body(containsString("<title>quarkus-undertow - 1.0.0-SNAPSHOT</title>"))
        ;
    }

}