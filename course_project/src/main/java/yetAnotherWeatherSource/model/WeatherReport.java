package yetAnotherWeatherSource.model;

import lombok.Data;

@Data
public class WeatherReport {
    private WeatherReportDetails weatherReportDetails;
    private CurrentWeatherReport currentWeatherReport;
}
