package yetAnotherWeatherSourse.mockIntegration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import yetAnotherWeatherSource.exception.CityNotFoundException;
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
public class CurrentWeatherMockIntegrationTests extends MockIntegrationTests {

    @Test //Mock test that weather report has city
    public void shouldHaveCityNameInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo(city);
    }

    @Test //Mock test that weather report has coordinates
    public void shouldHaveCoordinatesInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCoordinates()).isNotNull();
    }

    @Test //Mock test that weather report has current weather
    public void shouldHaveCurrentWeatherBlockInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getCurrentWeatherReport()).isNotNull();
    }
}
