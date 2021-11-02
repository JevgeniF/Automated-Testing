package yetAnotherWeatherSource.api.response;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import yetAnotherWeatherSource.api.model.CoordinatesModel;
import yetAnotherWeatherSource.api.model.MainModel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {
    private String name;
    private CoordinatesModel coord;
    private Integer dt;
    private Integer timezone;
    private MainModel main;
}
