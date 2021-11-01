package icd0004.raw;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.net.HttpURLConnection.HTTP_OK;

public class AuthenticationTests {

    public static final String API_URL = "https://restful-booker.herokuapp.com/auth";

    @Test //Posts right credentials and checks if API returns HTTP OK(200) status code.
    public void postAuthenticationWithRightCredentialsShouldReturnHttpOk() {
        String payload = """
                {
                    "username" : "admin",
                    "password" : "password123"
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
