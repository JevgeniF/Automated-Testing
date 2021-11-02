package yetAnotherWeatherSourse.appFunctionality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.model.WeatherReport;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrentWeatherInAppFunctionalityTests {
    private static String city;
    private static WeatherApi weatherApi;
    private static YetAnotherWeatherSource yetAnotherWeatherSource;

    @BeforeAll
    static void SetUp() {
        city = "Berlin";
        weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    @Test
    public void InAppWeatherReportShouldHaveSectionWeatherReportDetails() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails()).isNotNull(
        );
    }

    @Test
    public void inAppWeatherReportDetailsShouldHaveSameCityAsInRequest() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails().getCity()).isEqualTo(city);
    }

    @Test
    public void inAppWeatherReportDetailsShouldHaveCoordinates() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails().getCoordinates()).isNotNull();
    }

    @Test
    public void inAppWeatherReportDetailsShouldHaveTemperatureUnits() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails().getTemperatureUnit()).isNotNull();
    }

    @Test
    public void inAppCurrentWeatherReportShouldHaveDate() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeather().getDate()).isNotNull();
    }

    @Test
    public void inAppCurrentWeatherReportShouldHaveTemperature() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeather().getTemperature()).isNotNull();
    }

    @Test
    public void inAppCurrentWeatherReportShouldHaveHumidity() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeather().getHumidity()).isNotNull();
    }

    @Test
    public void inAppCurrentWeatherReportShouldHavePressure() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeather().getPressure()).isNotNull();
    }

    @Test
    public void inAppWeatherReportCoordinatesInFormat_LatLon() throws CityNotFoundException {
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails().getCoordinates()).
                isEqualTo(currentWeatherData.getCoord().getLat() + ", " + currentWeatherData.getCoord().getLon());
    }

    @Test
    public void inAppWeatherReportTemperatureUnitsCorrespondToApiSettings() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        if (weatherApi.getUnits().equals("metric")) {
            assertThat(weatherReport.getReportDetails().getTemperatureUnit()).isEqualTo("Celsius");
        } else if (weatherApi.getUnits().equals("imperial")) {
            assertThat(weatherReport.getReportDetails().getTemperatureUnit()).isEqualTo("Fahrenheit");
        }
    }

    @Test
    public void inAppWeatherReportDateInFormat_yy_MM_dd() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeather().getDate()).matches("\\d{4}-\\d{2}-\\d{2}");
    }

    @Test
    public void inAppWeatherReportDateIsCurrentDate() throws CityNotFoundException {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeather().getDate()).matches(dateFormat.format(currentDate));
    }



}
