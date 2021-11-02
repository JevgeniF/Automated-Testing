package yetAnotherWeatherSource.api.response;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import yetAnotherWeatherSource.api.dto.ListDto;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastData {
    private ArrayList<ListDto> list;
}
