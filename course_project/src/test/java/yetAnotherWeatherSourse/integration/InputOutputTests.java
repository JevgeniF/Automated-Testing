package yetAnotherWeatherSourse.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.exception.CityNotFoundException;
import yetAnotherWeatherSource.exception.FileInputMissingException;
import yetAnotherWeatherSource.exception.FileNotFoundException;
import yetAnotherWeatherSource.exception.WrongInputFormatException;
import yetAnotherWeatherSource.inOut.InOut;
import yetAnotherWeatherSource.model.WeatherReport;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputOutputTests {
    private static final String INPUT_DATA_FOLDER = "./src/main/resources/test_data/input/";
    private static final String OUTPUT_DATA_FOLDER = "./src/main/resources/test_data/output/";
    private static YetAnotherWeatherSource yetAnotherWeatherSource;

    @BeforeAll
    public static void setUp() {
        WeatherApi weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    @Test
    @SneakyThrows
    public void appGeneratesWeatherReportForCityFromFileReader() {
        String city = InOut.getCityFromFile(INPUT_DATA_FOLDER + "city.txt");

        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo("Plymouth");

    }

    @Test
    public void FileReaderThrowsWrongInputFormatExceptionIfFileHasWrongFormat() {
        String exceptionErrorMessage = "Wrong file format. Should be txt file.";
        Exception exception = assertThrows(WrongInputFormatException.class, () ->
                InOut.getCityFromFile(INPUT_DATA_FOLDER + "wrong_format.pdf"));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);

    }

    @Test
    public void FileReaderThrowsFileNotFoundExceptionIfNoFileWithThisPathOrName() {
        String exceptionErrorMessage = "File not found.";
        Exception exception = assertThrows(FileNotFoundException.class, () ->
                InOut.getCityFromFile(INPUT_DATA_FOLDER + "no_such_file.txt"));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);

    }

    @Test
    public void FileReaderThrowsFileInputMissingExceptionIfFileNameIsNullOrEmptyString() {
        String exceptionErrorMessage = "No file specified for File Reader.";
        Exception exception = assertThrows(FileInputMissingException.class, () ->
                InOut.getCityFromFile(" "));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);

    }

    @Test
    public void FileWriterSavesJsonFile()
            throws WrongInputFormatException, FileNotFoundException, FileInputMissingException, CityNotFoundException {
        String city = InOut.getCityFromFile(INPUT_DATA_FOLDER + "city.txt");
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        InOut.saveToJson(OUTPUT_DATA_FOLDER, weatherReport);
        File outputFile = new File(OUTPUT_DATA_FOLDER + "Weather in Plymouth.json");
        assertTrue(outputFile.exists() && outputFile.getName().endsWith(".json"));

    }
}
