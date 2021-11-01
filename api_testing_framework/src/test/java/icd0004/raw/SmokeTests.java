package icd0004.raw;

import icd0004.raw.interfaces.IBookingApi;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.HTTP_OK;

public class SmokeTests implements IBookingApi {

    @Test
    public void getBookingsShouldReturnHttpOKResponse() {
        when()
                .get(API_URL)
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void getBookingByIdShouldReturnHttpOKResponse() {
        given()
                .contentType(JSON.toString())
                .when()
                .get(API_URL + "/11")
                .then()
                .statusCode(HTTP_OK);
    }

    @Test
    public void postBookingsShouldReturnHttpOKResponse() {
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
                .statusCode(HTTP_OK);
    }
}
