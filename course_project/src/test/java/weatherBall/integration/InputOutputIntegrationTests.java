package weatherBall.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import weatherBall.WeatherBall;
import weatherBall.api.WeatherApi;
import weatherBall.exception.FileInputMissingException;
import weatherBall.exception.FileIsEmptyException;
import weatherBall.exception.FileNotFoundException;
import weatherBall.exception.WrongInputFormatException;
import weatherBall.inOut.InOut;
import weatherBall.model.WeatherReport;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for integration of FileReader and FileWriter from InOut Class with app.
 * 10 tests.
 */
public class InputOutputIntegrationTests {
    private static final String INPUT_DATA_FOLDER = "./src/main/resources/test_data/input/";
    private static final String OUTPUT_DATA_FOLDER = "./src/main/resources/test_data/output/";
    private static WeatherBall weatherBall;

    @BeforeAll
    public static void setUp() {
        WeatherApi weatherApi = new WeatherApi();
        weatherBall = new WeatherBall(weatherApi);
        File outputDirectory = new File(OUTPUT_DATA_FOLDER);
        File batchDirectory = new File(OUTPUT_DATA_FOLDER + "/batch");
        File batchErrDirectory = new File("./src/main/resources/test_data/output/batch_error");
        outputDirectory.mkdir();
        batchDirectory.mkdir();
        batchErrDirectory.mkdir();
    }

    @Test
    @SneakyThrows
    public void appGeneratesWeatherReportForCityFromFileReader() {
        ArrayList<String> cityList = InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "city.txt");
        ArrayList<WeatherReport> weatherReportList = weatherBall.getWeatherReport(cityList);

        assertThat(weatherReportList.get(0).getWeatherReportDetails().getCity()).isEqualTo("Plymouth");

    }

    @Test
    @SneakyThrows
    public void appGeneratesWeatherReportsForMultipleCitiesFromFileReader() {
        ArrayList<String> cityList = InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "cities.txt");

        ArrayList<WeatherReport> weatherReportList = weatherBall.getWeatherReport(cityList);

        assertThat(weatherReportList.size()).isEqualTo(3);
    }

    @Test
    @SneakyThrows
    public void appGeneratesWeatherReportsForMultipleValidCitiesFromFileReaderEvenIfInvalidCityInFile() {
        ArrayList<String> cityList = InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "cities_with_error.txt");

        ArrayList<WeatherReport> weatherReportList = weatherBall.getWeatherReport(cityList);

        InOut.saveToJson(OUTPUT_DATA_FOLDER + "batch_error/", weatherReportList);

        int jsonFileCount = Objects.requireNonNull(new File(OUTPUT_DATA_FOLDER + "batch_error/")
                .listFiles(file -> file.getName().endsWith(".json"))).length;

        assertThat(jsonFileCount).isEqualTo(3);
    }

    @Test
    public void fileReaderThrowsWrongInputFormatExceptionForInputIfFileNotTxt() {
        String exceptionErrorMessage = "Wrong file format. Should be txt file.";

        Exception exception = assertThrows(WrongInputFormatException.class, () ->
                InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "wrong_format.pdf"));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);

    }

    @Test
    public void fileReaderThrowsFileNotFoundExceptionIfForInputIfFileNotFound() {
        String exceptionErrorMessage = "File not found.";

        Exception exception = assertThrows(FileNotFoundException.class, () ->
                InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "no_such_file.txt"));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }

    @Test
    public void fileReaderThrowsFileIsMissingExceptionForInputIfFileWasEmpty() {
        String exceptionErrorMessage = "File is empty.";

        Exception exception = assertThrows(FileIsEmptyException.class, () ->
                InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "empty_file.txt"));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }

    @Test
    public void fileReaderThrowsFileInputMissingExceptionForInputIfFileNameIsEmptyString() {
        String exceptionErrorMessage = "No file specified for File Reader.";

        Exception exception = assertThrows(FileInputMissingException.class, () ->
                InOut.getCitiesFromFile("   "));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }

    @Test
    @SneakyThrows
    public void fileWriterSavesJsonFile() {
        String city = InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "city.txt").get(0);
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);

        InOut.saveToJson(OUTPUT_DATA_FOLDER, weatherReport);

        File outputFile = new File(OUTPUT_DATA_FOLDER + "Weather in Plymouth.json");

        assertTrue(outputFile.exists() && outputFile.getName().endsWith(".json"));

    }

    @Test
    @SneakyThrows
    public void fileWriterSavesJsonFileWithCityNameInNameOfFile() {
        String city = InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "city.txt").get(0);
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);

        InOut.saveToJson(OUTPUT_DATA_FOLDER, weatherReport);

        File outputFile = new File(OUTPUT_DATA_FOLDER + "Weather in Plymouth.json");

        assertTrue(outputFile.exists() && outputFile.getName().contains("Plymouth"));

    }

    @Test
    @SneakyThrows
    public void batchFileWriterSavesSeparateJsonFileForEveryCityWeatherReport() {
        ArrayList<String> cityList = InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "cities.txt");

        ArrayList<WeatherReport> weatherReportList = weatherBall.getWeatherReport(cityList);

        InOut.saveToJson(OUTPUT_DATA_FOLDER + "batch/", weatherReportList);

        int jsonFileCount = Objects.requireNonNull(new File(OUTPUT_DATA_FOLDER + "batch/")
                .listFiles(file -> file.getName().endsWith(".json"))).length;

        assertThat(jsonFileCount).isEqualTo(3);

    }

}
