package yetAnotherWeatherSource.api.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainModel {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
}
