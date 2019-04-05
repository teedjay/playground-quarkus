package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class NiruServiceTest {

    @Test
    void hello() {
        given()
            .when().get("/niru")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("text", is("Her er data som kommer i retyur"))
        ;
    }

}