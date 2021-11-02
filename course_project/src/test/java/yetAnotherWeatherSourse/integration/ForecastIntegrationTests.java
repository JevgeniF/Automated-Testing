package yetAnotherWeatherSourse.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.ForecastData;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for integration of Forecast API.
 * Check if received forecast data is right and complete.
 * 5 tests.
 */
public class ForecastIntegrationTests {
    static String city;
    static WeatherApi weatherApi;
    static ForecastData forecastData;

    @SneakyThrows
    @BeforeAll // initial setup for all tests
    static void SetUp() {
        city = "London";
        weatherApi = new WeatherApi();
        forecastData = weatherApi.getForecastData(city);
    }

    @Test //Test checks if response status is HTTP_OK(200), if request with city name sent and such city exists in API
    public void shouldReturnHttpOkWhenCityNameIsGivenAndCityExistsInApi() {
        int RequestStatus = WeatherApi.getForecastResponse(city).getStatus();

        assertThat(RequestStatus).isEqualTo(HTTP_OK);
    }

    @Test //Tests that API returns error message "City not found." if response was HTTP_NOT_FOUND(404)
    public void shouldReturnCityNotFoundErrorMessageWhenHttpNotFound() {
        String wrongCity = "Winterfell";
        String exceptionErrorMessage = "City not found.";
        Exception exception = assertThrows(CityNotFoundException.class, () ->
                weatherApi.getForecastData(wrongCity));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }

    @Test //Tests that API returns at least one Forecast from list in Forecast Data with temperature.
    public void shouldHaveTemperatureInAtLeastInOneBlockForecastData() {
        Double temperature = forecastData.getList().get(0).getMain().getTemp();

        assertThat(temperature).isNotNull();
    }

    @Test //Tests that API returns at least one Forecast from list in Forecast Data with humidity.
    public void shouldHaveHumidityInAtLeastInOneBlockForecastData() {
        int humidity = forecastData.getList().get(0).getMain().getHumidity();

        assertThat(humidity).isNotNull();
    }

}
