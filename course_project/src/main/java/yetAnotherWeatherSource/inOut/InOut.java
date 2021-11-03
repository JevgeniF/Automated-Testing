package yetAnotherWeatherSource.inOut;

import ch.qos.logback.classic.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
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

    static Logger ioLogger = (Logger) LoggerFactory.getLogger("I/O interface");

    public static ArrayList<String> getCitiesFromFile(String fileName)
            throws WrongInputFormatException, FileNotFoundException, FileInputMissingException, FileIsEmptyException {

        if (fileName.trim().isEmpty()) {
            ioLogger.error("Exception occurred when tried to get cities form input file: {} ",
                    new FileInputMissingException().getMessage());

            throw new FileInputMissingException();
        }

        if (!fileName.endsWith(".txt")) {
            ioLogger.error("Exception occurred when tried to get cities form input file: {} ",
                    new WrongInputFormatException().getMessage());

            throw new WrongInputFormatException();
        }

        ArrayList<String> cityList = new ArrayList<>();
        try (Stream <String> stream = Files.lines(Path.of(fileName))) {
            stream.forEach(city -> cityList.add(city.trim()));
        } catch (IOException e) {
            ioLogger.error("Exception occurred when tried to get cities form input file: {} ",
                    new FileNotFoundException().getMessage());

            throw new FileNotFoundException();
        }

        if (cityList.isEmpty()) {
            ioLogger.error("Exception occurred when tried to get cities form input file: {} ",
                    new FileIsEmptyException().getMessage());

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
            ioLogger.error("IOException occurred when tried to save .json files: {} ", e.getMessage());
        }
    }

    //overloaded method for batch save
    public static void saveToJson(String fileLocationPath, ArrayList<WeatherReport> weatherReportList) {
        for (WeatherReport weatherReport: weatherReportList) {
            saveToJson(fileLocationPath, weatherReport);

            ioLogger.info("Overloaded method used to save Json files");
        }
    }
}
