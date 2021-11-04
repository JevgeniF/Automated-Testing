package yetAnotherWeatherSourse.mockIntegration;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import yetAnotherWeatherSource.model.WeatherReport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Mock tests for integration of CurrentWeather API in app.
 * Check if Weather Report has data, as from Weather API.
 * 3 tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherMockIntegrationTests extends MockIntegration {

    @Test
    @SneakyThrows
    public void appWeatherReportDetailsShouldHaveSameCityNameAsMock() {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);

        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo(city);
    }

    @Test
    @SneakyThrows
    public void appWeatherReportDetailsShouldHaveCoordinatesFromMockData() {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);

        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCoordinates()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appWeatherReportHasCurrentWeatherReportFromMockData() {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getCurrentWeatherReport()).isNotNull();
    }
}
