package yetAnotherWeatherSource.model;

import lombok.Data;

@Data
public class ForecastWeather {
    private Double temperature;
    private Integer humidity;
    private Integer pressure;
}
