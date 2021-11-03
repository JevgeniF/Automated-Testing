package yetAnotherWeatherSource.api.response;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import yetAnotherWeatherSource.api.dto.ListDto;

import java.util.ArrayList;

/**
 * Class for generation of entities from API response with required to be forecast data only.
 * Attributes:
 * list - Arrays of entity of ListDto, for list section of API response
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastData {
    private ArrayList<ListDto> list;
}
