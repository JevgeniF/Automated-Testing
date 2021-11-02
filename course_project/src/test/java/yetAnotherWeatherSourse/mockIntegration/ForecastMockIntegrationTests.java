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
 * 3 tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class ForecastMockIntegrationTests extends MockIntegrationTests {

    @Test //Mock test that weather report has Forecast Report list block
    public void shouldHaveForecastReportListInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport()).isNotNull();
    }

    @Test //Mock test that weather report has Forecast Report List with at least one Forecast Report with Date
    public void shouldHaveForecastWithDateInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport().get(0).getDate()).isNotNull();
    }

    @Test //Mock test that weather report has Forecast Report List with at least one Forecast Report with Weather Data
    public void shouldHaveWeatherDataInForecastReportInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        when(weatherApi.getForecastData(anyString())).thenReturn(forecastData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport().get(0).getForecastWeather()).isNotNull();
    }


}
