package yetAnotherWeatherSource.api.dto;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDto {
    private Integer dt;
    private MainDto main;
}
