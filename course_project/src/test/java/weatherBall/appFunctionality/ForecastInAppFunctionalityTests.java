package weatherBall.appFunctionality;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import weatherBall.WeatherBall;
import weatherBall.api.WeatherApi;
import weatherBall.api.dto.MainDto;
import weatherBall.helpers.Helpers;
import weatherBall.model.Weather;
import weatherBall.model.WeatherReport;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for inApp Functionality implemented by use of Forecast API.
 * 14 tests consists of:
 * 3 tests for Forecast Report structure
 * 4 tests for existence of items in Weather Report
 * 7 tests for items format and content
 */
public class ForecastInAppFunctionalityTests {
    private static String city;
    private static WeatherBall weatherBall;

    @BeforeAll
    static void setUp() {
        city = "Florence";
        WeatherApi weatherApi = new WeatherApi();
        weatherBall = new WeatherBall(weatherApi);
    }

    //----------------------- STRUCTURE TESTS -----------------------//

    @Test
    @SneakyThrows
    public void appWeatherReportShouldHaveForecastReport() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appForecastReportListShouldHaveMinimumOneForecastReport() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0)).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appForecastReportFromForecastReportListShouldHaveWeather() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getWeather()).isNotNull();
    }

    //----------------------- ITEMS EXISTENCE TESTS -----------------------//

    @Test
    @SneakyThrows
    public void appForecastReportFromForecastReportListShouldHaveDate() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getDate()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appWeatherFromForecastReportShouldHaveTemperature() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getWeather().getTemperature()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appWeatherFromForecastReportShouldHaveHumidity() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getWeather().getHumidity()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appWeatherFromForecastReportShouldHavePressure() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getWeather().getPressure()).isNotNull();
    }

    //----------------------- ITEMS FORMAT AND CONTENT TESTS -----------------------//

    @Test
    @SneakyThrows
    public void appForecastReportListsShouldHave3ForecastReports() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertEquals(weatherReport.getForecastReport().size(), 3);
    }

    @Test
    @SneakyThrows
    public void appForecastReportsFromForecastReportListShouldBeInAscendingOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tomorrow = dateFormat.format(Date.from(Instant.now().plus(Duration.ofDays(1))));
        String dayAfterTomorrow = dateFormat.format(Date.from(Instant.now().plus(Duration.ofDays(2))));
        String dayAfterTomorrowPlusDay = dateFormat.format(Date.from(Instant.now().plus(Duration.ofDays(3))));

        WeatherReport weatherReport = weatherBall.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport().get(0).getDate()).matches(tomorrow);
        assertThat(weatherReport.getForecastReport().get(1).getDate()).matches(dayAfterTomorrow);
        assertThat(weatherReport.getForecastReport().get(2).getDate()).matches(dayAfterTomorrowPlusDay);
    }

    @Test
    @SneakyThrows
    public void appForecastReportShouldHaveDatesInFormat_yyyy_MM_dd() {
        WeatherReport weatherReport = weatherBall.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getDate()).matches("\\d{4}-\\d{2}-\\d{2}");
    }

    @Test
    @SneakyThrows
    public void inAppFirstForecastReportShouldBeForTomorrow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tomorrow = dateFormat.format(Date.from(Instant.now().plus(Duration.ofDays(1))));

        WeatherReport weatherReport = weatherBall.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport().get(0).getDate()).matches(tomorrow);
    }

    @Test //Test checks getAverageWeather method with artificial data
    public void appMethodCalculatesAverageTemperatureForWeatherFromWeatherReport() {
        ArrayList<MainDto> artificialMainDtoList = getArtificialMainDtoList();
        Weather weather = Helpers.getAverageForecastWeather(artificialMainDtoList);

        assertThat(weather.getTemperature()).isEqualTo(0.5);
    }

    @Test //Test checks getAverageWeather method with artificial data
    public void appMethodCalculatesAveragePressureForWeatherFromWeatherReport() {
        ArrayList<MainDto> artificialMainDtoList = getArtificialMainDtoList();
        Weather weather = Helpers.getAverageForecastWeather(artificialMainDtoList);
        assertThat(weather.getPressure()).isEqualTo(200);
    }

    @Test //Test checks getAverageWeather method with artificial data
    public void appMethodCalculatesAverageHumidityForWeatherFromWeatherReport() {
        ArrayList<MainDto> artificialMainDtoList = getArtificialMainDtoList();
        Weather weather = Helpers.getAverageForecastWeather(artificialMainDtoList);
        assertThat(weather.getHumidity()).isEqualTo(1100);
    }

    //----------------------- HELPER METHODS -----------------------//

    private ArrayList<MainDto> getArtificialMainDtoList() {
        return new ArrayList<>(Arrays.asList(
                new MainDto(0.0, 100, 1000),
                new MainDto(0.5, 200, 1100),
                new MainDto(1.0, 300, 1200)));
    }
}
