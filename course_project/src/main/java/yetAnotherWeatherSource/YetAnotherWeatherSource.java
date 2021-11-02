package yetAnotherWeatherSource;

import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.dto.ListDto;
import yetAnotherWeatherSource.api.dto.MainDto;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.api.response.ForecastData;
import yetAnotherWeatherSource.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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

        WeatherReportDetails weatherReportDetails = getReportDetails(currentWeatherData);
        CurrentWeatherReport currentWeatherReport = getCurrentWeather(currentWeatherData);
        ArrayList<ForecastReport> forecastReportList = getForecastReportList(forecastData);

        weatherReport.setWeatherReportDetails(weatherReportDetails);
        weatherReport.setCurrentWeatherReport(currentWeatherReport);
        weatherReport.setForecastReport(forecastReportList);

        return weatherReport;
    }

    private ArrayList<ForecastReport> getForecastReportList(ForecastData data) {
        ArrayList<ForecastReport> forecastReportList = new ArrayList<>();
        Map<Integer, ArrayList<MainDto>> forecastWeatherMap = getForecastWeatherMap(data);

        forecastWeatherMap.forEach((key, value) -> {
           ForecastReport forecastReport = new ForecastReport();
           forecastReport.setDate(key);
           forecastReportList.add(forecastReport);
        });

        return forecastReportList;
    }

    private Map<Integer, ArrayList<MainDto>> getForecastWeatherMap(ForecastData data) {
        Map<Integer,ArrayList<MainDto>> forecastWeatherMap = new LinkedHashMap<>();

        for (ListDto list: data.getList()) {
            if (checkDifferenceInDates(System.currentTimeMillis(), list.getDt())) {
                if (!forecastWeatherMap.containsKey(list.getDt()))
                    forecastWeatherMap.put(list.getDt(), new ArrayList<>());
                forecastWeatherMap.get(list.getDt()).add(list.getMain());
            }
        }
        return forecastWeatherMap;
    }

    private boolean checkDifferenceInDates(Long currentTimeStamp, Integer forecastTimeStamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(new Date(currentTimeStamp * 1000L));
        String forecastDate = dateFormat.format(new Date(forecastTimeStamp * 1000L));

        return !todayDate.equals(forecastDate);
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
