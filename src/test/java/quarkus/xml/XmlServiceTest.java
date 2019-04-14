package quarkus.xml;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class XmlServiceTest {

    @Test
    public void hello() {
        given()
            .when().get("/xml")
            .then()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("XmlData.message.text()", is("Here are some return xml data"))
        ;
    }

}