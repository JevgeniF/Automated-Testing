package icd0004.raw;

import icd0004.raw.interfaces.IBookingApi;
import icd0004.raw.interfaces.IUserAuthenticator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.HTTP_OK;

public class UpdateBookingTest implements IBookingApi, IUserAuthenticator {


    @Test
    public void putBookingShouldReturnHttpOKResponse() {
        int bookingId = 10;

        String payload = """
                {
                    "firstname": "Agent",
                    "lastname": "Smith",
                    "totalprice": 999,
                    "depositpaid": true,
                    "bookingdates": {
                    "checkin": "1999-03-31",
                    "checkout": "2003-11-05"
                        },
                    "additionalneeds": "You all in The Matrix..."
                }""";

        given()
                .accept(JSON.toString())
                .contentType(JSON.toString())
                .body(payload)
                .when()
                .put(API_URL + bookingId)
                .then()
                .statusCode(HTTP_OK);
    }
}
