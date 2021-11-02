package yetAnotherWeatherSourse.mockIntegration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.model.CoordinatesModel;
import yetAnotherWeatherSource.api.model.MainModel;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.model.WeatherReport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherMockIntegrationTests {
    @Mock
    static WeatherApi weatherApi;
    private String city;
    private CurrentWeatherData currentWeatherData;
    private YetAnotherWeatherSource yetAnotherWeatherSource;

    @Before
    public void setUp() {
        city = "Haabneeme";
        Integer date = 1635846093;
        Integer timezone = 7200;
        CoordinatesModel coordinates = new CoordinatesModel(24.8211, 59.5114);
        MainModel currentWeather = new MainModel(8.74, 1010, 75);

        currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setName(city);
        currentWeatherData.setCoord(coordinates);
        currentWeatherData.setDt(date);
        currentWeatherData.setTimezone(timezone);
        currentWeatherData.setMain(currentWeather);

        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }


    @Test
    public void shouldHaveCityNameInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getReportDetails().getCity()).isEqualTo(city);
    }

    @Test
    public void ShouldHaveCoordinatesInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getReportDetails().getCoordinates()).isNotNull();
    }

    @Test
    public void ShouldHaveCurrentWeatherBlockInWeatherReport() throws CityNotFoundException {
        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getCurrentWeather()).isNotNull();
    }
}
