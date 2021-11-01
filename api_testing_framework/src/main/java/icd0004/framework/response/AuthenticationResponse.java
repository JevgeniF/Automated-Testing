package icd0004.framework.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String statusCode = null;
    private String reason = null;
    private String token = null;
}
