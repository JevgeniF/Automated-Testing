package yetAnotherWeatherSource.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesModel {
    private Double lon;
    private Double lat;
}
