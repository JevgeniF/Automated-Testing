package icd0004.raw.interfaces;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;

public interface IBookingChangeTest {

    @BeforeAll
    // Update requires authenticate user and get token.
    static void authenticate() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/auth";
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("password123");
        RestAssured.authentication = authScheme;
    }
}
