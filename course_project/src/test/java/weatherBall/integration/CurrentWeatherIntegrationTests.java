package weatherBall.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import weatherBall.api.WeatherApi;
import weatherBall.api.response.CurrentWeatherData;
import weatherBall.exception.CityNotFoundException;

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

    @BeforeAll
    static void setUp() {
        city = "London";
        weatherApi = new WeatherApi();
    }

    @Test
    public void apiShouldReturnHttpOkWhenCityNameIsGivenAndCityExistsInApi() {
        int requestStatus = WeatherApi.getCurrentWeatherResponse(city).getStatus();

        assertThat(requestStatus).isEqualTo(HTTP_OK);
    }

    @Test
    public void apiShouldReturnCityNotFoundErrorMessageWhenCityNotFoundAndStatusHttpNotFound() {
        String wrongCity = "Winterfell";
        String exceptionErrorMessage = "City not found.";
        Exception exception = assertThrows(CityNotFoundException.class, () ->
                weatherApi.getCurrentWeatherData(wrongCity));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }

    @Test
    @SneakyThrows
    public void apiShouldReturnSameCityNameInCurrentWeatherDataAsInRequest() {
        currentWeatherData = weatherApi.getCurrentWeatherData(city);
        assertThat(currentWeatherData.getName()).isEqualTo(city);
    }

    @Test
    @SneakyThrows
    public void fetchedCurrentWeatherDataShouldHaveCoordinates() {
        currentWeatherData = weatherApi.getCurrentWeatherData(city);
        assertThat(currentWeatherData.getCoord()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void fetchedCurrentWeatherDataShouldHaveMainData() {
        currentWeatherData = weatherApi.getCurrentWeatherData(city);
        assertThat(currentWeatherData.getMain()).isNotNull();
    }


}
