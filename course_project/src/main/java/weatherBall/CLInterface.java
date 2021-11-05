package weatherBall;

import org.apache.commons.io.FilenameUtils;
import weatherBall.api.WeatherApi;
import weatherBall.exception.*;
import weatherBall.inOut.InOut;
import weatherBall.model.WeatherReport;

import java.util.ArrayList;

public class CLInterface {

    private static WeatherBall weatherBall;

    public static void main(String[] args) throws WrongInputFormatException, FileIsEmptyException, FileNotFoundException, FileInputMissingException {
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

    private static void stdOut(String input) {
        if (input.trim().isEmpty()) {
            System.out.println("Input not found.");
            return;
        }

        if (FilenameUtils.getExtension(input).isEmpty()) {
            try {
                System.out.println(weatherBall.getWeatherReport(input).toString());
            } catch (CityNotFoundException e) {
                System.out.printf("%s city not found!", input);
            }
        } else {
            try {
                ArrayList<String> cityList = InOut.getCitiesFromFile(input);
                ArrayList<WeatherReport> weatherReportList = weatherBall.getWeatherReport(cityList);

                for (WeatherReport weatherReport : weatherReportList) {
                    System.out.println(weatherReport.toString());
                }
            } catch (WrongInputFormatException e) {
                System.out.println("File has wrong format. Only txt allowed.");
            } catch (FileIsEmptyException e) {
                System.out.println("File is empty.");
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            } catch (FileInputMissingException ignore) {
            }
        }
    }

    private static void jsonOut(String input, String jsonPath) {
        if (input.trim().isEmpty()) {
            System.out.println("Input not found.");
            return;
        }
        if (FilenameUtils.getExtension(input).isEmpty()) {
            try {
                WeatherReport weatherReport = weatherBall.getWeatherReport(input);
                InOut.saveToJson(jsonPath, weatherReport);
            } catch (CityNotFoundException e) {
                System.out.printf("%s city not found!", input);
            }
        } else {
            try {
                ArrayList<String> cityList = InOut.getCitiesFromFile(input);
                ArrayList<WeatherReport> weatherReportList = weatherBall.getWeatherReport(cityList);

                InOut.saveToJson(jsonPath, weatherReportList);
            } catch (WrongInputFormatException e) {
                System.out.println("File has wrong format. Only txt allowed.");
            } catch (FileIsEmptyException e) {
                System.out.println("File is empty.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (FileInputMissingException ignore) {
            }
        }
    }
}
