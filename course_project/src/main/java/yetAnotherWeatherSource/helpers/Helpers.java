package yetAnotherWeatherSource.helpers;

import yetAnotherWeatherSource.api.dto.ListDto;
import yetAnotherWeatherSource.api.dto.MainDto;
import yetAnotherWeatherSource.api.response.ForecastData;
import yetAnotherWeatherSource.model.Weather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class holds additional methods (helpers) used in YetAnotherWeatherSource (main app) class
 */
public class Helpers {

    /**
     * Method gets average meanings for temperature, humidity, pressure from Array List of MainDto entities and
     * returns Weather class entity with calculated meaning, required for Forecast Report in app.
     *
     * @param mainDtoList - Array List of MainDto entities from ForecastData received from API
     * @return Weather class entity
     */
    public static Weather getAverageForecastWeather(ArrayList<MainDto> mainDtoList) {
        Weather averageWeather = new Weather();
        averageWeather.setTemperature(mainDtoList.stream().mapToDouble(MainDto::getTemp).average().orElseThrow());
        averageWeather.setHumidity((int) mainDtoList.stream().mapToDouble(MainDto::getHumidity).average().orElseThrow());
        averageWeather.setPressure((int) mainDtoList.stream().mapToDouble(MainDto::getPressure).average().orElseThrow());
        return averageWeather;
    }

    /**
     * Method returns Map with date as String and Array List of MainDto entities from ForecastData entity.
     * Uses 3 next dates as keys, and collects MainDto entities as value for these days(keys) only
     *
     * @param data - ForecastData entity, received by request from API
     * @return Map with dates as keys, and MainDto entities as values
     */
    public static Map<String, ArrayList<MainDto>> getForecastWeatherMap(ForecastData data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, ArrayList<MainDto>> forecastWeatherMap = new LinkedHashMap<>();

        String todayDate = dateFormat.format(new Date(System.currentTimeMillis()));

        for (ListDto list : data.getList()) {
            String forecastDate = dateFormat.format(new Date(list.getDt() * 1000L));
            if (forecastWeatherMap.size() == 3) break;
            if (!forecastDate.equals(todayDate)) {
                if (!forecastWeatherMap.containsKey(forecastDate))
                    forecastWeatherMap.put(forecastDate, new ArrayList<>());
                forecastWeatherMap.get(forecastDate).add(list.getMain());
            }
        }
        return forecastWeatherMap;
    }
}
