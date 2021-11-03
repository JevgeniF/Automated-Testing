package icd0004.framework;

import icd0004.framework.request.Authentication;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class AuthenticationApi {

    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String AUTH_API = BASE_URL + "/auth/";

    public static Response postAuthentication(Authentication authenticationCredentials) {
        return given()
                .contentType(JSON.toString())
                .accept(JSON.toString())
                .body(authenticationCredentials)
                .when()
                .post(AUTH_API);
    }

    public static void authenticate() {
        Authentication credentials = Authentication.getRealCredentials();
        RestAssured.baseURI = AUTH_API;
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(credentials.getUsername());
        authScheme.setPassword(credentials.getPassword());
        RestAssured.authentication = authScheme;
    }
}
