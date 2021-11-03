package yetAnotherWeatherSourse.integration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.response.ForecastData;
import yetAnotherWeatherSource.exception.CityNotFoundException;

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

    @BeforeAll
    static void SetUp() {
        city = "London";
        weatherApi = new WeatherApi();
    }

    @Test
    public void apiShouldReturnHttpOkWhenCityNameIsGivenAndCityExistsInApi() {
        int RequestStatus = WeatherApi.getForecastResponse(city).getStatus();

        assertThat(RequestStatus).isEqualTo(HTTP_OK);
    }

    @Test
    public void apiShouldReturnCityNotFoundErrorMessageWhenCityNotFoundAndStatusHttpNotFound() {
        String wrongCity = "Winterfell";
        String exceptionErrorMessage = "City not found.";
        Exception exception = assertThrows(CityNotFoundException.class, () ->
                weatherApi.getForecastData(wrongCity));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }

    @Test
    @SneakyThrows
    public void fetchedForecastDataShouldHaveTemperatureInMainData() {
        forecastData = weatherApi.getForecastData(city);
        Double temperature = forecastData.getList().get(0).getMain().getTemp();

        assertThat(temperature).isNotNull();
    }

    @Test
    @SneakyThrows
    public void fetchedForecastDataShouldHaveHumidityInMainData() {
        forecastData = weatherApi.getForecastData(city);
        int humidity = forecastData.getList().get(0).getMain().getHumidity();

        assertThat(humidity).isNotNull();
    }

    @Test
    @SneakyThrows
    public void fetchedForecastDataShouldHavePressureInMainData() {
        forecastData = weatherApi.getForecastData(city);
        int pressure = forecastData.getList().get(0).getMain().getPressure();

        assertThat(pressure).isNotNull();
    }

}
