package yetAnotherWeatherSource.api.response;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import yetAnotherWeatherSource.api.model.CoordinatesModel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {
    private String name;
    private CoordinatesModel coord;
}
