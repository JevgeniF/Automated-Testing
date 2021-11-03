package icd0004.framework;

import icd0004.framework.request.Authentication;
import icd0004.framework.response.AuthenticationResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;


public class AuthenticationTests {

    static Authentication realCredentials;
    static Authentication fakeCredentials;

    @BeforeAll
    static void getCredentials() {
        realCredentials = Authentication.getRealCredentials();
        fakeCredentials = Authentication.getFakeCredentials();
    }

    @Test //Posts right credentials and checks if API returns HTTP OK(200) status code.
    public void postAuthenticationWithRightCredentialsShouldReturnHttpOk() {
        int statusCode = AuthenticationApi
                .postAuthentication(realCredentials).getStatusCode();

        assertThat(statusCode).isEqualTo(HTTP_OK);
    }

    @Test //Posts right credentials and checks if API returns token in body.
    public void postAuthenticationWithRightCredentialsShouldReturnToken() {
        AuthenticationResponse authenticationResponse = AuthenticationApi
                .postAuthentication(realCredentials)
                .as(AuthenticationResponse.class);

        assertThat(authenticationResponse.getToken()).isNotNull();
    }

    @Test //Posts wrong credentials and checks if API returns "Bad credentials" message in body.
    public void postAuthenticationWithWrongCredentialsShouldReturnBadCredentialsMessage() {
        AuthenticationResponse authenticationResponse = AuthenticationApi
                .postAuthentication(fakeCredentials)
                .as(AuthenticationResponse.class);

        assertThat(authenticationResponse.getReason()).isEqualTo("Bad credentials");
    }
}
