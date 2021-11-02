package yetAnotherWeatherSourse.mockIntegration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.dto.CoordDto;
import yetAnotherWeatherSource.api.dto.MainDto;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
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
public class CurrentWeatherMockIntegrationTests {
    @Mock
    static WeatherApi weatherApi;

    private String city;
    private CurrentWeatherData currentWeatherData;
    private YetAnotherWeatherSource yetAnotherWeatherSource;

    @Before //initial setup to avoid code repeats, as used in all tests.
    public void setUp() {
        city = "Haabneeme";
        Integer date = 1635846093;
        CoordDto coordinates = new CoordDto(24.8211, 59.5114);
        MainDto currentWeather = new MainDto(8.74, 1010, 75);

        currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setName(city);
        currentWeatherData.setCoord(coordinates);
        currentWeatherData.setDt(date);
        currentWeatherData.setMain(currentWeather);

        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }


    @Test //Mock test that weather report has city
    public void shouldHaveCityNameInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo(city);
    }

    @Test //Mock test that weather report has coordinates
    public void shouldHaveCoordinatesInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCoordinates()).isNotNull();
    }

    @Test //Mock test that weather report has current weather
    public void shouldHaveCurrentWeatherBlockInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getCurrentWeatherReport()).isNotNull();
    }
}
