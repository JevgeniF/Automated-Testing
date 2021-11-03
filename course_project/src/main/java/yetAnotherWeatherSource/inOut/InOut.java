package yetAnotherWeatherSource.inOut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InOut {
    public static String getCityFromFile(String filePath) throws IOException {
        Path fileLocation = Path.of(filePath);
        return Files.readString(fileLocation).trim();
    }
}
