package yetAnotherWeatherSource;

import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.dto.MainDto;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.api.response.ForecastData;
import yetAnotherWeatherSource.helpers.Helpers;
import yetAnotherWeatherSource.model.CurrentWeatherReport;
import yetAnotherWeatherSource.model.ForecastReport;
import yetAnotherWeatherSource.model.WeatherReport;
import yetAnotherWeatherSource.model.WeatherReportDetails;

import java.util.ArrayList;
import java.util.Map;

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
        ForecastData forecastData = weatherApi.getForecastData(city);

        WeatherReportDetails weatherReportDetails = getWeatherReportDetails(currentWeatherData);
        CurrentWeatherReport currentWeatherReport = getCurrentWeatherReport(currentWeatherData);
        ArrayList<ForecastReport> forecastReportList = getForecastReportList(forecastData);

        weatherReport.setWeatherReportDetails(weatherReportDetails);
        weatherReport.setCurrentWeatherReport(currentWeatherReport);
        weatherReport.setForecastReport(forecastReportList);

        return weatherReport;
    }

    /**
     * Method gets Array List of ForecastReport class entity, which used as attribute of WeatherReport Class.
     *
     * @param data - ForecastData class entity created from API response
     * @return Array List of ForecastReport entities
     */
    private ArrayList<ForecastReport> getForecastReportList(ForecastData data) {
        ArrayList<ForecastReport> forecastReportList = new ArrayList<>();
        Map<String, ArrayList<MainDto>> forecastWeatherMap = Helpers.getForecastWeatherMap(data);

        forecastWeatherMap.forEach((key, value) -> {
            ForecastReport forecastReport = new ForecastReport();
            forecastReport.setDate(key);
            forecastReport.setWeather(Helpers.getAverageForecastWeather(value));
            forecastReportList.add(forecastReport);
        });

        return forecastReportList;
    }

    /**
     * Method creates CurrentWeatherReport class entity, which used as attribute of Weather Report class.
     *
     * @param data - CurrentWeatherData class entity created from API response
     * @return CurrentWeatherReport class entity
     */
    private CurrentWeatherReport getCurrentWeatherReport(CurrentWeatherData data) {
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
    private WeatherReportDetails getWeatherReportDetails(CurrentWeatherData data) {
        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();

        weatherReportDetails.setCity(data.getName());
        weatherReportDetails.setCoordinates(data.getCoord());
        weatherReportDetails.setTemperatureUnit(weatherApi.getUnits());

        return weatherReportDetails;
    }
}
