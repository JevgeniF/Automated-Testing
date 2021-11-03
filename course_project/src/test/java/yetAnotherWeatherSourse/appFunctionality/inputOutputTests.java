package yetAnotherWeatherSourse.appFunctionality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.inOut.InOut;
import yetAnotherWeatherSource.model.WeatherReport;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class inputOutputTests {
    private static WeatherApi weatherApi;
    private static YetAnotherWeatherSource yetAnotherWeatherSource;
    private static final String INPUT_DATA_FOLDER = "./src/main/resources/test_data/Input/";

    @BeforeAll
    public static void setUp() {
        weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    @Test
    public void inAppGetWeatherReportForCityFromFile() throws CityNotFoundException, IOException {
        String city = InOut.getCityFromFile(INPUT_DATA_FOLDER + "city.txt");

        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo("Plymouth");

    }

    @Test
    public void inAppThrowsWrongInputFormatExceptionIfFileHasWrongFormat() throws CityNotFoundException, IOException {
        String exceptionErrorMessage = "Wrong file format. Should be txt file.";
        Exception exception = assertThrows(WrongInputFormatException.class, () ->
                InOut.getCityFromFile(INPUT_DATA_FOLDER + "wrong_format.pdf"));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);

    }
}
