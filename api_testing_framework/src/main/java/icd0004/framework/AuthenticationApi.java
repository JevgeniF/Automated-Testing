package icd0004.framework;

import icd0004.framework.request.Authentication;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthenticationApi {

    public static final String AUTH_URL = "https://restful-booker.herokuapp.com/auth";

    public static Response postAuthentication(Authentication authenticationCredentials) {
        return given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(authenticationCredentials)
                .when()
                .post(AUTH_URL);
    }
}
