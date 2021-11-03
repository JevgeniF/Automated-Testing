package yetAnotherWeatherSource.inOut;

import org.codehaus.jackson.map.ObjectMapper;
import yetAnotherWeatherSource.exception.FileInputMissingException;
import yetAnotherWeatherSource.exception.FileNotFoundException;
import yetAnotherWeatherSource.exception.WrongInputFormatException;
import yetAnotherWeatherSource.model.WeatherReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InOut {
    public static String getCityFromFile(String fileName)
            throws WrongInputFormatException, FileNotFoundException, FileInputMissingException {

        if(fileName.trim().isEmpty()) {
            throw new FileInputMissingException();
        }

        if (!fileName.endsWith(".txt")) {
            throw new WrongInputFormatException();
        }


        Path fileLocation = Path.of(fileName);
        try {
            return Files.readString(fileLocation).trim();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
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
}
