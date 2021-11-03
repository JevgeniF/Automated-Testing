package yetAnotherWeatherSourse.appFunctionality;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.model.WeatherReport;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for inApp Functionality implemented by use of Current Weather API.
 * 13 tests consists of:
 * 2 tests for Weather Report structure
 * 7 tests for existence of items in Weather Report
 * 4 tests for items format and content
 */
public class CurrentWeatherInAppFunctionalityTests {
    private static String city;
    private static WeatherApi weatherApi;
    private static YetAnotherWeatherSource yetAnotherWeatherSource;


    @BeforeAll
    static void setUp() {
        city = "Berlin";
        weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    //----------------------- STRUCTURE TESTS -----------------------//

    @Test
    @SneakyThrows
    public void appWeatherReportShouldHaveWeatherReportDetails() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appWeatherReportShouldHaveCurrentWeatherReport() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport()).isNotNull();
    }

    //----------------------- ITEMS EXISTENCE TESTS -----------------------//

    @Test
    @SneakyThrows
    public void appWeatherReportDetailsShouldHaveSameCityAsInRequest() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo(city);
    }

    @Test
    @SneakyThrows
    public void appWeatherReportDetailsShouldHaveCoordinates() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails().getCoordinates()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appWeatherReportDetailsShouldHaveTemperatureUnits() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails().getTemperatureUnit()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appCurrentWeatherReportShouldHaveDate() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getDate()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appCurrentWeatherReportShouldHaveTemperature() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getTemperature()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void appCurrentWeatherReportShouldHaveHumidity() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getHumidity()).isNotNull();
    }

    @Test
    @SneakyThrows
    public void inAppCurrentWeatherReportShouldHavePressure() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getPressure()).isNotNull();
    }

    //----------------------- ITEMS FORMAT AND CONTENT TESTS -----------------------//

    @Test
    @SneakyThrows
    public void appWeatherReportDetailsShouldHaveCoordinatesInFormat_LatLon() {
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getWeatherReportDetails().getCoordinates()).
                isEqualTo(currentWeatherData.getCoord().getLat() + "," + currentWeatherData.getCoord().getLon());
    }

    @Test
    @SneakyThrows
    public void appWeatherReportDetailsTemperatureUnitsCorrespondToApiUnitsSetting() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        //Implemented test with if, as separate tests will cause case when 1 test always will have error.
        if (weatherApi.getUnits().equals("metric")) {
            assertThat(weatherReport.getWeatherReportDetails().getTemperatureUnit()).isEqualTo("Celsius");
        } else if (weatherApi.getUnits().equals("imperial")) {
            assertThat(weatherReport.getWeatherReportDetails().getTemperatureUnit()).isEqualTo("Fahrenheit");
        }
    }

    @Test
    @SneakyThrows
    public void appCurrentWeatherReportShouldHaveDateInFormat_yyyy_MM_dd() {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getDate()).matches("\\d{4}-\\d{2}-\\d{2}");
    }

    @Test
    @SneakyThrows
    public void appCurrenWeatherReportDateIsCurrentDate() {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getDate()).matches(dateFormat.format(currentDate));
    }
}
