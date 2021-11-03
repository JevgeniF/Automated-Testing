package yetAnotherWeatherSourse.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.exception.FileNotFoundException;
import yetAnotherWeatherSource.exception.WrongInputFormatException;
import yetAnotherWeatherSource.inOut.InOut;
import yetAnotherWeatherSource.model.WeatherReport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class inputOutputTests {
    private static final String INPUT_DATA_FOLDER = "./src/main/resources/test_data/Input/";
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
}
