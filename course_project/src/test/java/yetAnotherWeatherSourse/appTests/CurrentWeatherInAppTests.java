package yetAnotherWeatherSourse.appTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.model.WeatherReport;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrentWeatherInAppTests {
    private static String city;
    private static YetAnotherWeatherSource yetAnotherWeatherSource;

    @BeforeAll
    static void SetUp() {
        city = "Berlin";
        WeatherApi weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    @Test
    public void inAppWeatherReportShouldHaveSameCityAsInRequest() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails().getCity()).isEqualTo(city);
    }
}
