package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class CustomMetricsTest {

    @Test
    public void makeSureWeFindOutCustomMetrics() {

        // make two requests so that the counter is 2.0
        given().when().get("/hello").then().statusCode(200);
        given().when().get("/hello").then().statusCode(200);

        given()
            .when().get("/metrics/application")
            .then()
                .statusCode(200)
                .contentType(ContentType.TEXT)
                .body(containsString("my_custom_hello_counter 2.0"))
        ;
    }

}
