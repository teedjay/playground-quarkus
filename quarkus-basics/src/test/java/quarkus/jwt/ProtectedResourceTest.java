package quarkus.jwt;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ProtectedResourceTest {

    @Test
    public void makeSureMissingTokenReturnsUnauthorized() {
        given()
            .header("Authorization", "Bearer illegal-token-value")
            .when().get("/secret")
            .then()
                .statusCode(401)
        ;
    }

}