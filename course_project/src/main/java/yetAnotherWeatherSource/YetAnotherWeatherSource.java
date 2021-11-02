package yetAnotherWeatherSource;

import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;

public class YetAnotherWeatherSource {

    private final WeatherApi weatherApi;

    public YetAnotherWeatherSource(WeatherApi weatherApi) { this.weatherApi = weatherApi; }

    public WeatherReport getWeatherReport(String city) throws CityNotFoundException {
        WeatherReport weatherReport = new WeatherReport();
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);

        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();
        weatherReportDetails.setCity(currentWeatherData.getName());
        weatherReport.setWeatherReportDetails(weatherReportDetails);

        return weatherReport;
    }
}
