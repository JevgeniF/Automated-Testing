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

public class CurrentWeatherIntegrationTests {
    static String city;
    static WeatherApi weatherApi;
    static CurrentWeatherData currentWeatherData;

    @BeforeAll
    @SneakyThrows
    static void SetUp() {
        city = "Haabneeme";
        weatherApi = new WeatherApi();
        currentWeatherData = weatherApi.getCurrentWeatherData(city);
    }

    @Test
    public void ShouldReturnHttpOkWhenCityNameIsGiven() {
        int RequestStatus = WeatherApi.getCurrentWeatherResponse(city).getStatus();

        assertThat(RequestStatus).isEqualTo(HTTP_OK);
    }

    @Test
    public void ShouldReturnSameCityNameInWeatherData() {
        assertThat(currentWeatherData.getName()).isEqualTo(city);
    }

    @Test
    public void ShouldHaveCoordinatesInWeatherData() {
        assertThat(currentWeatherData.getCoord()).isNotNull();
    }

    @Test
    public void ShouldHaveMainDataBlockInWeatherData() {
        assertThat(currentWeatherData.getMain()).isNotNull();
    }

    @Test
    public void ShouldReturnCityNotFoundErrorMessageWhenCityNotFound() {
        String wrongCity = "Winterfell";
        String exceptionErrorMessage = "City not found.";
        Exception exception = assertThrows(CityNotFoundException.class, () ->
                weatherApi.getCurrentWeatherData(wrongCity));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }

}
