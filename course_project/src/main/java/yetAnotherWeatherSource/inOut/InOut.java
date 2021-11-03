package yetAnotherWeatherSource.inOut;

import yetAnotherWeatherSource.exception.FileNotFoundException;
import yetAnotherWeatherSource.exception.WrongInputFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InOut {
    public static String getCityFromFile(String fileName) throws WrongInputFormatException, FileNotFoundException {

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
}
