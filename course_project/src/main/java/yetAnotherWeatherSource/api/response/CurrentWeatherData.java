package yetAnotherWeatherSource.api.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {
    private String name;
}
