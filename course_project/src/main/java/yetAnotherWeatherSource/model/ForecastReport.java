package yetAnotherWeatherSource.model;

import lombok.Data;

@Data
public class ForecastReport {
    private String date;
    private Weather weather;
}
