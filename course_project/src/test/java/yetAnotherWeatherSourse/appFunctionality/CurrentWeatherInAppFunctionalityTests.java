package yetAnotherWeatherSourse.appFunctionality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.model.WeatherReport;

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
    public void inAppWeatherReportShouldHaveSameCityAsInRequest() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails().getCity()).isEqualTo(city);
    }

    @Test
    public void inAppWeatherReportShouldHaveCoordinates() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails().getCoordinates()).isNotNull();
    }

    @Test
    public void inAppWeatherReportShouldHaveTemperatureUnits() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getReportDetails().getTemperatureUnit()).isNotNull();
    }

    @Test
    public void inAppWeatherReportShouldHaveDate() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeather().getDate()).isNotNull();
    }

    @Test
    public void inAppWeatherReportShouldHaveCoordinatesInFormatLatLon() throws CityNotFoundException {
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



}
