package yetAnotherWeatherSourse.mockIntegration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.model.WeatherReport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Mock tests for integration of CurrentWeather API in app.
 * Check if Weather Report has data, as from Weather API.
 * 4 tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class ForecastMockIntegrationTests extends MockIntegrationTests {

    @Test //Mock test that weather report has Forecast block
    public void shouldHaveForecastInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getForecast()).isNotNull;
    }
}
