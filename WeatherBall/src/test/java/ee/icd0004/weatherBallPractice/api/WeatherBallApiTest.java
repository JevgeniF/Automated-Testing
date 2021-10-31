package ee.icd0004.weatherBallPractice.api;

import ee.icd0004.weatherBallPractice.WeatherBall;
import ee.icd0004.weatherBallPractice.WeatherReport;
import ee.icd0004.weatherBallPractice.exception.CityNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;


public class WeatherBallApiTest {

    private static WeatherBall weatherBall;
    private static WeatherApi weatherApi;
    private static String city;

    @BeforeClass
    public static void setUp() {
        weatherBall = new WeatherBall(new WeatherApi());
        weatherApi = new WeatherApi();
        city = "Pärnu";
    }

    @Test
    public void shouldHaveMainDetailsInWeatherReport() throws CityNotFoundException {
        WeatherReport weatherReport = weatherBall.getWeatherReportForCity(city);

        assertThat(weatherReport.getMainDetails()).isNotNull();
    }

    @Test
    public void shouldReturnCityNameInCurrentWeatherData_whenCorrectCityNameIsGiven() throws CityNotFoundException {
        CurrentWeatherData weatherData = weatherApi.getCurrentWeatherData(city);

        assertThat(weatherData.getName()).isEqualTo(city);
    }

    @Test
    public void shouldReturnErrorMessage_whenCityNotFound() {
        String wrongCity = "alieK";
        String exceptionErrorMessage = "Provided city name not found.";
        Exception exception = assertThrows(CityNotFoundException.class, () -> weatherBall.getWeatherReportForCity(wrongCity));

        assertThat(exception.getMessage()).isEqualTo(exceptionErrorMessage);
    }
}
