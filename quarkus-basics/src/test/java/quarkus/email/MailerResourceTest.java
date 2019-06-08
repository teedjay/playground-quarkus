package quarkus.email;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class MailerResourceTest {

    /*
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <violationReport>
      <parameterViolations>
          <constraintType>PARAMETER</constraintType>
          <path>sendCrazyMailWithRandomAttachments.arg0</path>
          <message>must not be null</message>
          <value></value>
      </parameterViolations>
    </violationReport>
    */

    @Test
    void testNullAttachments() {
        given()
            .queryParam("email", "test@nowhe.re")
        .when()
            .get("/mailer")
        .then()
            .statusCode(200)
            .contentType(ContentType.XML)
            .body("MailerStatus.subject.text()", is("Crazy Mail To test@nowhe.re"))
            .body("MailerStatus.attachments.size()", is(0))
        ;
    }

    @Test
    void testSomeAttachments() {
        given()
            .queryParam("email", "test@somewhe.re")
            .queryParam("numberOfAttachments", 4)
        .when()
            .get("/mailer")
        .then()
            .statusCode(200)
            .contentType(ContentType.XML)
            .body("MailerStatus.subject.text()", is("Crazy Mail To test@somewhe.re"))
            .body("MailerStatus.attachments.size()", is(4))
        ;
    }

    @Test
    void testTooFewAttachments() {
        given()
            .queryParam("email", "test@somewhe.re")
            .queryParam("numberOfAttachments", -1)
        .when()
            .get("/mailer")
        .then()
            .statusCode(400)
            .contentType(ContentType.XML)
            .body("violationReport.parameterViolations.message.text()", is("must be greater than or equal to 0"))
        ;
    }

    @Test
    void testTooManyAttachments() {
        given()
            .queryParam("email", "test@somewhe.re")
            .queryParam("numberOfAttachments", 7)
        .when()
            .get("/mailer")
        .then()
            .statusCode(400)
            .contentType(ContentType.XML)
            .body("violationReport.parameterViolations.message.text()", is("must be less than or equal to 5"))
        ;
    }

    @Test
    void testIllegalEmail() {
        given()
            .queryParam("email", "thisisnotanemailaddreess")
            .queryParam("numberOfAttachments", 0)
        .when()
            .get("/mailer")
        .then()
            .statusCode(400)
            .contentType(ContentType.XML)
            .body("violationReport.parameterViolations.message.text()", is("must be a well-formed email address"))
        ;
    }

}

