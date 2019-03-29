package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UploadResourceTest {

    @Test
    void uploadMultiPartData() {
        given()
            .multiPart("file", new File("pom.xml"))
            .multiPart("data", "teedjay")
            .when().post("/upload")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", is(2))
                .body("[0].mediaType", is("application/octet-stream"))
                .body("[1].content", is("teedjay"))
        ;
    }

}