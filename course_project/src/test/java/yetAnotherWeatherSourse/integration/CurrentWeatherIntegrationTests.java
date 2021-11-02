package yetAnotherWeatherSourse.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for integration of CurrentWeather API.
 * Check if received weather data is right and complete.
 * 5 tests.
 */
public class CurrentWeatherIntegrationTests {
    static String city;
    static WeatherApi weatherApi;
    static CurrentWeatherData currentWeatherData;

    @SneakyThrows
    @BeforeAll // initial setup for all tests
    static void setUp() {
        city = "London";
        weatherApi = new WeatherApi();
        currentWeatherData = weatherApi.getCurrentWeatherData(city);
    }

    @Test //Test checks if response status is HTTP_OK(200), if request with city name sent and such city exists in API
    public void shouldReturnHttpOkWhenCityNameIsGivenAndCityExistsInApi() {
        int RequestStatus = WeatherApi.getCurrentWeatherResponse(city).getStatus();

        assertThat(RequestStatus).isEqualTo(HTTP_OK);
    }

    @Test //Tests if API returns response with city name and city name is the same as in request.
    public void shouldReturnSameCityNameInWeatherData() {
        assertThat(currentWeatherData.getName()).isEqualTo(city);
    }

    @Test //Tests if API returns response with coordinates
    public void shouldHaveCoordinatesInWeatherData() {
        assertThat(currentWeatherData.getCoord()).isNotNull();
    }

    @Test //Tests if API returns response with main weather block
    public void shouldHaveMainDataBlockInWeatherData() {
        assertThat(currentWeatherData.getMain()).isNotNull();
    }

    @Test //Tests that API returns error message "City not found." if response was HTTP_NOT_FOUND(404)
    public void shouldReturnCityNotFoundErrorMessageWhenHttpNotFound() {
        String wrongCity = "Winterfell";
        String exceptionErrorMessage = "City not found.";
        Exception exception = assertThrows(CityNotFoundException.class, () ->
                weatherApi.getCurrentWeatherData(wrongCity));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }

}
