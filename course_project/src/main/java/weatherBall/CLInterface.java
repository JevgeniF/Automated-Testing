package weatherBall;

import weatherBall.api.WeatherApi;
import weatherBall.exception.CityNotFoundException;

public class CLInterface {

    public static void main(String[] args) throws CityNotFoundException {
        WeatherApi weatherApi = new WeatherApi();
        WeatherBall weatherBall = new WeatherBall(weatherApi);

        if (args[0].equals("-console")) {
            System.out.println(weatherBall.getWeatherReport(args[1]).toString());
        }
    }
}
