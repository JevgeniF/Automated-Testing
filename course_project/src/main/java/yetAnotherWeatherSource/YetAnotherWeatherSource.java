package yetAnotherWeatherSource;

import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.model.CurrentWeatherReport;
import yetAnotherWeatherSource.model.WeatherReport;
import yetAnotherWeatherSource.model.WeatherReportDetails;

/**
 * Class with main methods of application.
 * Generates in app weather report from API response.
 * Attributes:
 * weatherApi - entity of WeatherApi class with main attributes and methods of API
 */
@SuppressWarnings("ClassCanBeRecord")
public class YetAnotherWeatherSource {

    private final WeatherApi weatherApi;

    /**
     * Class constructor
     *
     * @param weatherApi - entity of WeatherApi class with main attributes and methods of API
     */
    public YetAnotherWeatherSource(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    /**
     * Method creates WeatherReport class entity, which is main class in app output.
     *
     * @param city - name of city
     * @return WeatherReport class entity
     * @throws CityNotFoundException in case if city name not found in API
     */
    public WeatherReport getWeatherReport(String city) throws CityNotFoundException {
        WeatherReport weatherReport = new WeatherReport();
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);

        WeatherReportDetails weatherReportDetails = getReportDetails(currentWeatherData);

        CurrentWeatherReport currentWeatherReport = getCurrentWeather(currentWeatherData);

        weatherReport.setWeatherReportDetails(weatherReportDetails);
        weatherReport.setCurrentWeatherReport(currentWeatherReport);

        return weatherReport;
    }

    /**
     * Method creates CurrentWeatherReport class entity, which used as attribute of Weather Report class.
     *
     * @param data - CurrentWeatherData class entity created from API response
     * @return CurrentWeatherReport class entity
     */
    private CurrentWeatherReport getCurrentWeather(CurrentWeatherData data) {
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport();

        currentWeatherReport.setDate(data.getDt());
        currentWeatherReport.setTemperature(data.getMain().getTemp());
        currentWeatherReport.setHumidity(data.getMain().getHumidity());
        currentWeatherReport.setPressure(data.getMain().getPressure());

        return currentWeatherReport;
    }

    /**
     * Method creates WeatherReportDetails class entity, which used as attribute of Weather Report class.
     *
     * @param data - CurrentWeatherData class entity created from API response
     * @return WeatherReportDetails class entity
     */
    private WeatherReportDetails getReportDetails(CurrentWeatherData data) {
        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();

        weatherReportDetails.setCity(data.getName());
        weatherReportDetails.setCoordinates(data.getCoord());
        weatherReportDetails.setTemperatureUnit(weatherApi.getUnits());

        return weatherReportDetails;
    }
}
