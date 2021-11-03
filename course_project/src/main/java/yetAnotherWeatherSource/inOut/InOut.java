package yetAnotherWeatherSource.inOut;

import yetAnotherWeatherSource.exception.WrongInputFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InOut {
    public static String getCityFromFile(String filePath) throws IOException, WrongInputFormatException {

        if (!filePath.endsWith(".txt")) {
            throw new WrongInputFormatException();
        }

        Path fileLocation = Path.of(filePath);
        return Files.readString(fileLocation).trim();
    }
}
