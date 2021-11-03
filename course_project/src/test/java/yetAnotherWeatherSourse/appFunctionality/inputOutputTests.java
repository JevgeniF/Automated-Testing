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

public class inputOutputTests {
    private static WeatherApi weatherApi;
    private static YetAnotherWeatherSource yetAnotherWeatherSource;

    @BeforeAll
    public static void setUp() {
        weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    @Test
    public void inAppGetWeatherReportForCityFromFile() throws CityNotFoundException, IOException {
        String city = InOut.getCityFromFile("./src/main/resources/test_data/Input/city.txt");

        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo("Plymouth");

    }
}
