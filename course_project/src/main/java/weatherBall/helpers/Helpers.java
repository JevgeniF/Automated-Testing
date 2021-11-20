package weatherBall.helpers;

import weatherBall.api.dto.ListDto;
import weatherBall.api.dto.MainDto;
import weatherBall.api.response.ForecastData;
import weatherBall.model.Weather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class holds additional methods (helpers) used in WeatherBall (main app) class
 */
public class Helpers {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Weather getAverageForecastWeather(ArrayList<MainDto> mainDtoList) {
        Weather averageWeather = new Weather();
        averageWeather.setTemperature(mainDtoList.stream().mapToDouble(MainDto::getTemp).average().orElseThrow());
        averageWeather.setHumidity((int) mainDtoList.stream().mapToDouble(MainDto::getHumidity).average().orElseThrow());
        averageWeather.setPressure((int) mainDtoList.stream().mapToDouble(MainDto::getPressure).average().orElseThrow());
        return averageWeather;
    }


    public static Map<String, ArrayList<MainDto>> getForecastWeatherMap(ForecastData data) {

        Map<String, ArrayList<MainDto>> forecastWeatherMap = new LinkedHashMap<>();
        String currentDate = formattedCurrentDate();

        formatThreeDaysForecastWeatherMap(data, forecastWeatherMap, currentDate);

        return forecastWeatherMap;
    }

    private static void formatThreeDaysForecastWeatherMap(ForecastData data,
                                                          Map<String, ArrayList<MainDto>> forecastWeatherMap,
                                                          String currentDate) {
        for (ListDto list : data.getList()) {
            String forecastDate = formattedForecastDate(list.getDt());
            if (isForecastWeatherMapIsFull(forecastWeatherMap)) break;
            if (isForecastDateNotCurrentDate(forecastDate, currentDate)) {
                addDateAndForecastToForecastWeatherMap(forecastWeatherMap, forecastDate, list);
            }
        }
    }

    private static void addDateAndForecastToForecastWeatherMap(Map<String, ArrayList<MainDto>> forecastWeatherMap,
                                                               String forecastDate, ListDto forecastsList) {
        if (!forecastWeatherMap.containsKey(forecastDate)) {
            forecastWeatherMap.put(forecastDate, new ArrayList<>());
            forecastWeatherMap.get(forecastDate).add(forecastsList.getMain());
        }
    }

    private static String formattedCurrentDate() {
        return dateFormat.format(new Date(System.currentTimeMillis()));
    }

    private static String formattedForecastDate(Long date) {
        return dateFormat.format(new Date(date * 1000L));
    }

    private static boolean isForecastWeatherMapIsFull(Map<String, ArrayList<MainDto>> forecastWeatherMap) {
        return forecastWeatherMap.size() == 3;
    }

    private static boolean isForecastDateNotCurrentDate(String forecastDate, String currentDate) {
        return !forecastDate.equals(currentDate);
    }
}
