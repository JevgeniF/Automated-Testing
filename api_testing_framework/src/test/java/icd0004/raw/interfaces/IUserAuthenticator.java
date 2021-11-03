package icd0004.raw.interfaces;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeEach;


//RestAssured authentication with scheme for tests where user authentication required.
public interface IUserAuthenticator {

    @BeforeEach
    // Update requires authenticate user and get token.
    default void authenticate() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/auth";
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("password123");
        RestAssured.authentication = authScheme;
    }
}
