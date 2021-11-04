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
 * Mock tests for integration of Forecast API in app.
 * Check if Weather Report has data, as from Weather API.
 * 3 tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class ForecastMockIntegrationTests extends MockIntegration {

    @Test
    @SneakyThrows
    public void appWeatherReportShouldHaveForecastReportListFromMockData() {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appForecastReportListShouldHaveForecastWithData() {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport().get(0).getDate()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appForecastReportListShouldHaveForecastWithWeather() {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport().get(0).getWeather()).isNotNull();
    }


}
