package yetAnotherWeatherSource.model;

import lombok.Data;

@Data
public class WeatherReport {
    private ReportDetails reportDetails;
    private CurrentWeather currentWeather;
}
