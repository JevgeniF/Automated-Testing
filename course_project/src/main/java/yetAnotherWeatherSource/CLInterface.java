package yetAnotherWeatherSource;

import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.exception.CityNotFoundException;

public class CLInterface {

    public static void main(String[] args) throws CityNotFoundException {
        WeatherApi weatherApi = new WeatherApi();
        YetAnotherWeatherSource yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);

        if (args[0].equals("-console")) {
            System.out.println(yetAnotherWeatherSource.getWeatherReport(args[1]).toString());
        }
    }
}
