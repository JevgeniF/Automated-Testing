package weatherBall.api.response;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import weatherBall.api.dto.CoordDto;
import weatherBall.api.dto.MainDto;

/**
 * Class for generation of entities from API response with required current weather data only.
 * Attributes:
 * name - city name
 * coord - entity of CoordDto, coord section of response with coordinates
 * dt - date, Unix
 * main - entity of MainDto, main section of response with main weather data
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {
    private String name;
    private CoordDto coord;
    private Long dt;
    private MainDto main;
}
