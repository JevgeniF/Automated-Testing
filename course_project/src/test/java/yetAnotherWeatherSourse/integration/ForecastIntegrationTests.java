package yetAnotherWeatherSourse.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.api.WeatherApi;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for integration of Forecast API.
 * Check if received forecast data is right and complete.
 * 5 tests.
 */
public class ForecastIntegrationTests {
    static String city;
    static WeatherApi weatherApi;
    //static ForecastData forecastData;

    @SneakyThrows
    @BeforeAll // initial setup for all tests
    static void SetUp() {
        city = "London";
        weatherApi = new WeatherApi();
        //forecastData = weatherApi.getForecastData(city);
    }

    @Test //Test checks if response status is HTTP_OK(200), if request with city name sent and such city exists in API
    public void ShouldReturnHttpOkWhenCityNameIsGivenAndCityExistsInApi() {
        int RequestStatus = WeatherApi.getForecastResponse(city).getStatus();

        assertThat(RequestStatus).isEqualTo(HTTP_OK);
    }

}
