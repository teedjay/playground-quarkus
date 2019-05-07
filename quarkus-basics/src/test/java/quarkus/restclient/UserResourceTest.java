package quarkus.restclient;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTest {

    @Test
    void getExternalUsers() {
        given()
            .when().get("/users")
            .then()
            .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", is(10))
                .body("name", hasItems("Nicholas Runolfsdottir V", "Kurtis Weissnat", "Chelsey Dietrich"))
        ;
    }

}