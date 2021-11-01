package yetAnotherWeatherSourse.integration;

import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class CurrentWeatherIntegrationTests {

    @Test
    public void ShouldReturnHttpOkWhenCityNameIsGiven() {
        String city = "Haabneeme";

        int RequestStatus = WeatherApi.getCurrentWeatherResponse(city).getStatus();
        assertThat(RequestStatus).isEqualTo(HTTP_OK);
    }

    @Test
    public void ShouldReturnSameCityNameInWeatherReportDetails() {
        String city = "Haabneeme";
        CurrentWeatherData currentWeatherData = WeatherApi.getCurrentWeatherData(city);

        assertThat(currentWeatherData.getName()).isEqualTo(city);
    }
}
