package weatherBall;

import weatherBall.api.WeatherApi;
import weatherBall.exception.CityNotFoundException;
import weatherBall.inOut.InOut;
import weatherBall.model.WeatherReport;

public class CLInterface {

    private static WeatherBall weatherBall;

    public static void main(String[] args) {
        WeatherApi weatherApi = new WeatherApi();
        weatherBall = new WeatherBall(weatherApi);

        String jsonPath = "";
        switch (args[0]) {
            case "-console" -> {
                switch (args[1]) {
                    case "-c" -> {
                        WeatherApi.setUnits("metric");
                        stdOut(args[2]);
                    }
                    case "-f" -> {
                        WeatherApi.setUnits("imperial");
                        stdOut(args[2]);
                    }
                }
                stdOut(args[1]);
            }
            case "-json" -> {
                switch (args[1]) {
                    case "-c" -> {
                        WeatherApi.setUnits("metric");
                        jsonOut(args[2], args.length == 3 ? jsonPath : args[3]);
                    }
                    case "-f" -> {
                        WeatherApi.setUnits("imperial");
                        jsonOut(args[2], args.length == 3 ? jsonPath : args[3]);
                    }
                }
                if (args.length == 2) {
                    jsonOut(args[1], jsonPath);
                } else if (args.length == 3) {
                    jsonOut(args[1], args[2]);
                }
            }
        }
    }

    private static void stdOut(String city) {
        try {
            System.out.println(weatherBall.getWeatherReport(city).toString());
        } catch (CityNotFoundException e) {
            System.out.printf("%s city not found!", city);
        }
    }

    private static void jsonOut(String city, String jsonPath) {
        try {
            WeatherReport weatherReport = weatherBall.getWeatherReport(city);
            InOut.saveToJson(jsonPath, weatherReport);
        } catch (CityNotFoundException e) {
            System.out.printf("%s city not found!", city);
        }
    }
}
