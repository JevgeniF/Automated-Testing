package yetAnotherWeatherSource.model;

import lombok.Data;

@Data
public class WeatherReportDetails {
    private String city;
    private String coordinates;
    private String temperatureUnit = "Celsius";
}
