package quarkus.panache;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UsersResourceTest {

    // These tests are disabled because when quarkus-smallrye-openapi in the pom.xml
    // both rest-assured test fails (running quarkus 0.17.0 on my Mac)

    @Test()
    @Disabled
    void getAllUsers() {
        given()
            .when().get("/users")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].username", is("string"))
        ;
    }

    @Test()
    @Disabled
    void makeSureWeGetAPageOfUsers() {
        given()
            .when().get("/users/page/1")    // second page, first page is 0
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", is(5))
                .body("[0].id", is(6))  // page size is 5, so the first element of page #2 should have id 6
        ;
    }

}