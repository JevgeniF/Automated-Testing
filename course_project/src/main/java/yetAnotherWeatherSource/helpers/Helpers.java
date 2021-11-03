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

    public static Weather getAverageForecastWeather(ArrayList<MainDto> mainDtoList) {
        Weather averageWeather = new Weather();
        averageWeather.setTemperature(mainDtoList.stream().mapToDouble(MainDto::getTemp).average().orElseThrow());
        averageWeather.setHumidity((int) mainDtoList.stream().mapToDouble(MainDto::getHumidity).average().orElseThrow());
        averageWeather.setPressure((int) mainDtoList.stream().mapToDouble(MainDto::getPressure).average().orElseThrow());
        return averageWeather;
    }

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
