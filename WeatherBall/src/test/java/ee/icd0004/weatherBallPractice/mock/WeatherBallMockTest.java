package ee.icd0004.weatherBallPractice.mock;

import ee.icd0004.weatherBallPractice.WeatherBall;
import ee.icd0004.weatherBallPractice.WeatherReport;
import ee.icd0004.weatherBallPractice.api.CurrentWeatherData;
import ee.icd0004.weatherBallPractice.api.WeatherApi;
import ee.icd0004.weatherBallPractice.exception.CityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherBallMockTest {

    @Mock
    static WeatherApi weatherApi;

    @Test
    public void shouldHaveCityNameInWeatherReport() throws CityNotFoundException {
        String city = "Pärnu";

        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setName(city);
        WeatherBall weatherBall = new WeatherBall(weatherApi);

        when(weatherApi.getCurrentWeatherData(anyString())).thenReturn(currentWeatherData);
        WeatherReport weatherReport = weatherBall.getWeatherReportForCity(city);

        assertThat(weatherReport.getMainDetails().getCity()).isEqualTo(city);
    }
}
