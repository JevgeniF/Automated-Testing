package yetAnotherWeatherSource.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesModel {
    private Double lon;
    private Double lat;
}
