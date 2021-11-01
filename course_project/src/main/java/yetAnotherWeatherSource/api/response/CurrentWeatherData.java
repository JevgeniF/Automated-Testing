package yetAnotherWeatherSource.api.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import lombok.Data;
import yetAnotherWeatherSource.api.model.CoordinatesModel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {
    private String name;
    private CoordinatesModel coord;
}
