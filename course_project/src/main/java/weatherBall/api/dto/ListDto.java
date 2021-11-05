package weatherBall.api.dto;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * DataTransferObject Class for "list" section of API response, which holds forecast date and weather.
 * Attributes:
 * dt - date of weather forecast in API response
 * main - MainDto entity, which holds temperature, humidity and pressure for the dt(date) from API response.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDto {
    private Long dt;
    private MainDto main;
}
