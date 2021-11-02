package yetAnotherWeatherSource;

import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.model.CurrentWeather;
import yetAnotherWeatherSource.model.WeatherReport;
import yetAnotherWeatherSource.model.WeatherReportDetails;

import java.util.Date;

public class YetAnotherWeatherSource {

    private final WeatherApi weatherApi;

    public YetAnotherWeatherSource(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public WeatherReport getWeatherReport(String city) throws CityNotFoundException {
        WeatherReport weatherReport = new WeatherReport();
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);

        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();
        weatherReportDetails.setCity(currentWeatherData.getName());
        weatherReportDetails.setCoordinates(currentWeatherData.getCoord().getLat() + ", "
                + currentWeatherData.getCoord().getLon());

        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setDate(new Date(currentWeatherData.getDt() * 1000L));
        currentWeather.setTemperature(currentWeatherData.getMain().getTemp());
        currentWeather.setHumidity(currentWeatherData.getMain().getHumidity());
        currentWeather.setPressure(currentWeatherData.getMain().getPressure());

        weatherReport.setWeatherReportDetails(weatherReportDetails);
        weatherReport.setCurrentWeather(currentWeather);

        return weatherReport;
    }
}
