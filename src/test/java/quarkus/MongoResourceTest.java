package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class MongoResourceTest {

    @Test
    void makeSureWeGetDefaltMongoDatabases() {
        given()
            .when().get("/mongo")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("names", hasItems("local", "config", "admin"))
                .body("count", greaterThanOrEqualTo(3))
                .body("names.size()", greaterThanOrEqualTo(3))
        ;
    }

}