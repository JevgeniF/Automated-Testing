package yetAnotherWeatherSourse.integration;

import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.api.WeatherApi;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.assertj.core.api.Assertions.assertThat;

public class CurrentWeatherIntegrationTests {

    @Test
    public void ShouldReturnHttpOkWhenCityNameIsGiven() {
        String city = "Haabneeme";

        int RequestStatus = WeatherApi.getCurrentWeatherResponse(city).getStatus();
        assertThat(RequestStatus).isEqualTo(HTTP_OK);
    }
}
