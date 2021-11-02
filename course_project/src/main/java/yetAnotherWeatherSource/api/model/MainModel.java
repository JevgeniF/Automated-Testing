package yetAnotherWeatherSource.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainModel {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
}
