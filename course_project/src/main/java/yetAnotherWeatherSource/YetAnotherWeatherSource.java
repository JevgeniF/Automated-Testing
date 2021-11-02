package yetAnotherWeatherSource;

import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.model.CurrentWeather;
import yetAnotherWeatherSource.model.ReportDetails;
import yetAnotherWeatherSource.model.WeatherReport;

@SuppressWarnings("ClassCanBeRecord")
public class YetAnotherWeatherSource {

    private final WeatherApi weatherApi;

    public YetAnotherWeatherSource(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public WeatherReport getWeatherReport(String city) throws CityNotFoundException {
        WeatherReport weatherReport = new WeatherReport();
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);

        ReportDetails reportDetails = getReportDetails(currentWeatherData);

        CurrentWeather currentWeather = getCurrentWeather(currentWeatherData);

        weatherReport.setReportDetails(reportDetails);
        weatherReport.setCurrentWeather(currentWeather);

        System.out.println(weatherReport);
        return weatherReport;
    }

    private CurrentWeather getCurrentWeather(CurrentWeatherData data) {
        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setDate(data.getDt());
        currentWeather.setTemperature(data.getMain().getTemp());
        currentWeather.setHumidity(data.getMain().getHumidity());
        currentWeather.setPressure(data.getMain().getPressure());

        return currentWeather;
    }

    private ReportDetails getReportDetails(CurrentWeatherData data) {
        ReportDetails reportDetails = new ReportDetails();

        reportDetails.setCity(data.getName());
        reportDetails.setCoordinates(data.getCoord());
        reportDetails.setTemperatureUnit(weatherApi.getUnits());

        return reportDetails;
    }
}
