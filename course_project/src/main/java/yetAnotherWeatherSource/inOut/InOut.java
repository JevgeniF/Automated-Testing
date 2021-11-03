package yetAnotherWeatherSource.inOut;

import org.codehaus.jackson.map.ObjectMapper;
import yetAnotherWeatherSource.exception.FileInputMissingException;
import yetAnotherWeatherSource.exception.FileIsEmptyException;
import yetAnotherWeatherSource.exception.FileNotFoundException;
import yetAnotherWeatherSource.exception.WrongInputFormatException;
import yetAnotherWeatherSource.model.WeatherReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Class holds methods requiter for File Read and File Write.
 */
public class InOut {

    public static ArrayList<String> getCitiesFromFile(String fileName)
            throws WrongInputFormatException, FileNotFoundException, FileInputMissingException, FileIsEmptyException {

        if (fileName.trim().isEmpty()) {
            throw new FileInputMissingException();
        }

        if (!fileName.endsWith(".txt")) {
            throw new WrongInputFormatException();
        }

        ArrayList<String> cityList = new ArrayList<>();
        try (Stream <String> stream = Files.lines(Path.of(fileName))) {
            stream.forEach(city -> cityList.add(city.trim()));
        } catch (IOException e) {
            throw new FileNotFoundException();
        }

        if (cityList.isEmpty()) {
            throw new FileIsEmptyException();
        }

        return cityList;
    }

    public static void saveToJson(String fileLocationPath, WeatherReport weatherReport) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(fileLocationPath + "Weather in " +
                            weatherReport.getWeatherReportDetails().getCity() +
                            ".json"),
                    weatherReport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //overloaded method for batch save
    public static void saveToJson(String fileLocationPath, ArrayList<WeatherReport> weatherReportList) {
        for (WeatherReport weatherReport: weatherReportList) {
            saveToJson(fileLocationPath, weatherReport);
        }
    }
}
