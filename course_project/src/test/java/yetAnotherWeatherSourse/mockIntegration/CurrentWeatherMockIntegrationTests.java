package yetAnotherWeatherSourse.mockIntegration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import yetAnotherWeatherSource.WeatherReport;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherMockIntegrationTests {

    @Mock
    static WeatherApi weatherApi;

    @Test
    public void shouldHaveCityNameInWeatherReport() {
        String city = "Pärnu";

        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setName(city);
        YetAnotherWeatherSource weatherSource = new YetAnotherWeatherSource(weatherApi);

        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = weatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo(city);
    }
}
