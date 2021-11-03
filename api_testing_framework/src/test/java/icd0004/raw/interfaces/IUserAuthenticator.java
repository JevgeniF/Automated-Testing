package icd0004.raw.interfaces;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeEach;

public interface IUserAuthenticator {

    @BeforeEach
    default void authenticate() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/auth";
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("password123");
        RestAssured.authentication = authScheme;
    }
}
