package yetAnotherWeatherSource.model;

import lombok.Data;

import java.util.ArrayList;

/**
 * Class for generation of entity from application output (JSON object).
 * Attributes:
 * weatherReportDetails - entity of WeatherReportDetails class, holds city name, coordinates, temperature unit
 * currentWeatherReport - entity of CurrentWeatherReport class, holds date, temperature, pressure, humidity
 */
@Data
public class WeatherReport {
    private WeatherReportDetails weatherReportDetails;
    private CurrentWeatherReport currentWeatherReport;
    private ArrayList<ForecastReport> forecastReport;

}
