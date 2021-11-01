package icd0004.framework;

import icd0004.framework.request.Authentication;
import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;


public class AuthenticationTests {

    @Test //Posts right credentials and checks if API returns HTTP OK(200) status code.
    public void postAuthenticationWithRightCredentialsShouldReturnHttpOk() {
        Authentication authenticationCredentials = Authentication.getRealCredentials();

        int statusCode = AuthenticationApi
                .postAuthentication(authenticationCredentials).getStatusCode();

        assertThat(statusCode).isEqualTo(HTTP_OK);
    }
}
