package yetAnotherWeatherSource;

import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.model.CurrentWeatherReport;
import yetAnotherWeatherSource.model.WeatherReport;
import yetAnotherWeatherSource.model.WeatherReportDetails;

@SuppressWarnings("ClassCanBeRecord")
public class YetAnotherWeatherSource {

    private final WeatherApi weatherApi;

    public YetAnotherWeatherSource(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public WeatherReport getWeatherReport(String city) throws CityNotFoundException {
        WeatherReport weatherReport = new WeatherReport();
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);

        WeatherReportDetails weatherReportDetails = getReportDetails(currentWeatherData);

        CurrentWeatherReport currentWeatherReport = getCurrentWeather(currentWeatherData);

        weatherReport.setWeatherReportDetails(weatherReportDetails);
        weatherReport.setCurrentWeatherReport(currentWeatherReport);

        return weatherReport;
    }

    private CurrentWeatherReport getCurrentWeather(CurrentWeatherData data) {
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport();

        currentWeatherReport.setDate(data.getDt());
        currentWeatherReport.setTemperature(data.getMain().getTemp());
        currentWeatherReport.setHumidity(data.getMain().getHumidity());
        currentWeatherReport.setPressure(data.getMain().getPressure());

        return currentWeatherReport;
    }

    private WeatherReportDetails getReportDetails(CurrentWeatherData data) {
        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();

        weatherReportDetails.setCity(data.getName());
        weatherReportDetails.setCoordinates(data.getCoord());
        weatherReportDetails.setTemperatureUnit(weatherApi.getUnits());

        return weatherReportDetails;
    }
}
