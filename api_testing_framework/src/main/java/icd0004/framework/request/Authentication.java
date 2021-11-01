package icd0004.framework.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
@Builder
public class Authentication {
    private String username;
    private String password;

    public static Authentication getRealCredentials() {
        return Authentication.builder()
                .username("admin")
                .password("password123")
                .build();
    }
}
