package icd0004.raw;

import icd0004.raw.interfaces.IBookingApi;
import icd0004.raw.interfaces.IUserAuthenticator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.HTTP_CREATED;

public class DeleteBookingTest implements IBookingApi, IUserAuthenticator {

    @Test
    public void deleteBookingShouldReturnHttpCreatedResponse() {
        int bookingId = 11;

        given()
                .accept(JSON.toString())
                .contentType(JSON.toString())
                .when()
                .delete(API_URL + bookingId)
                .then()
                .statusCode(HTTP_CREATED);
    }
}
