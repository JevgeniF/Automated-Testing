package yetAnotherWeatherSource.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * DataTransferObject Class for "main" section of API response, which holds main weather data.
 * Has AllArgsConstructor and NoArgsConstructor annotations required for use in Integration Mock tests.
 * Attributes:
 * temp - temperature
 * pressure - atmosphere pressure
 * humidity - humidity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainDto {
    private Double temp;
    private Integer pressure;
    private Integer humidity;
}
