package yetAnotherWeatherSourse.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class CurrentWeatherIntegrationTests {
    static String city;
    static CurrentWeatherData currentWeatherData;

    @BeforeAll
    static void getCurrentWeatherDataForCity() {
        city = "Haabneeme";
        currentWeatherData = WeatherApi.getCurrentWeatherData(city);
    }

    @Test
    public void ShouldReturnHttpOkWhenCityNameIsGiven() {
        int RequestStatus = WeatherApi.getCurrentWeatherResponse(city).getStatus();

        assertThat(RequestStatus).isEqualTo(HTTP_OK);
    }

    @Test
    public void ShouldReturnSameCityNameInWeatherReportDetails() {
        assertThat(currentWeatherData.getName()).isEqualTo(city);
    }

    @Test
    public void ShouldHaveCoordinatesInWeatherReportDetails() {
        assertThat(currentWeatherData.getCoord()).isNotNull();
    }
}
