package icd0004.raw;

import icd0004.raw.interfaces.IBookingApi;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateBookingTests implements IBookingApi {

    @Test
    public void postBookingShouldReturnBookingId() {
        String payload = """
                {
                    "firstname": "Jevgeni",
                    "lastname": "Fenko",
                    "totalprice": 12,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2021-10-30",
                        "checkout": "2021-10-31"
                    }
                }""";

        given()
                .accept(JSON.toString())
                .contentType(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .statusCode(HTTP_OK)
                .body("bookingid", notNullValue());
    }

    @Test
    public void postBookingShouldReturnCorrectBookingPerson() {
        String payload = """
                {
                    "firstname": "Jevgeni",
                    "lastname": "Fenko",
                    "totalprice": 12,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2021-10-30",
                        "checkout": "2021-10-31"
                    }
                }""";

        given()
                .accept(JSON.toString())
                .contentType(JSON.toString())
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .body("booking.firstname", equalTo("Jevgeni"))
                .body("booking.lastname", equalTo("Fenko"));
    }

    @Test
    public void postBookingWithWrongAcceptHeaderShouldReturnImATeapotError() {
        String acceptHeader = "application/pdf";

        String payload = """
                {
                    "firstname": "Jevgeni",
                    "lastname": "Fenko",
                    "totalprice": 12,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2021-10-30",
                        "checkout": "2021-10-31"
                    }
                }""";

        given()
                .accept(JSON.toString())
                .contentType(JSON.toString())
                .header("Accept", acceptHeader)
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .statusCode(418);
    }

}
