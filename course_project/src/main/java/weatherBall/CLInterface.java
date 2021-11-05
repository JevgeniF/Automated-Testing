package weatherBall;

import weatherBall.api.WeatherApi;
import weatherBall.exception.CityNotFoundException;

public class CLInterface {

    public static void main(String[] args) {
        WeatherApi weatherApi = new WeatherApi();
        WeatherBall weatherBall = new WeatherBall(weatherApi);

        if (args[0].equals("-console")) {
            try {
                System.out.println(weatherBall.getWeatherReport(args[1]).toString());
            } catch (CityNotFoundException e) {
                System.out.printf("%s city not found!", args[1]);
            }
        }

        if (args[1].equals("-console")) {
            if (args[0].equals("-f")) { WeatherApi.setUnits("imperial"); }
            try {
                System.out.println(weatherBall.getWeatherReport(args[2]).toString());
            } catch (CityNotFoundException e) {
                System.out.printf("%s city not found!", args[2]);
            }
        }
    }
}
