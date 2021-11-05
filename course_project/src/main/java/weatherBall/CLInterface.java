package weatherBall;

import weatherBall.api.WeatherApi;
import weatherBall.exception.CityNotFoundException;

public class CLInterface {

    private static WeatherBall weatherBall;

    public static void main(String[] args) {
        WeatherApi weatherApi = new WeatherApi();
        weatherBall = new WeatherBall(weatherApi);

        switch (args[0]) {
            case "-console":
                switch (args[1]) {
                    case "-c":
                        WeatherApi.setUnits("metric");
                        stdout(args[2]);
                        break;
                    case "-f":
                        WeatherApi.setUnits("imperial");
                        stdout(args[2]);
                        break;
                    default:
                        stdout(args[1]);
                        break;
                }
        }
    }

    private static void stdout(String inputAttribute) {
        try {
            System.out.println(weatherBall.getWeatherReport(inputAttribute).toString());
        } catch (CityNotFoundException e) {
            System.out.printf("%s city not found!", inputAttribute);
        }
    }
}
