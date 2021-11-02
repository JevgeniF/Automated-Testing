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


    @BeforeAll // initial setup for all tests
    static void setUp() {
        city = "Berlin";
        weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    //----------------------- STRUCTURE TESTS -----------------------//

    @Test //Checks if weather report has section with weather report details
    public void inAppWeatherReportShouldHaveSectionWeatherReportDetails() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails()).isNotNull();
    }

    @Test //Checks if weather report has section with current weather report
    public void inAppWeatherReportShouldHaveSectionCurrentWeatherReport() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport()).isNotNull();
    }

    //----------------------- ITEMS EXISTENCE TESTS -----------------------//

    @Test //Checks if weather report details section has city and the city is the same as in API request
    public void inAppWeatherReportDetailsShouldHaveSameCityAsInRequest() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails().getCity()).isEqualTo(city);
    }

    @Test //Checks if weather report details section has coordinates
    public void inAppWeatherReportDetailsShouldHaveCoordinates() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails().getCoordinates()).isNotNull();
    }

    @Test //Checks if weather report details section has temperature units
    public void inAppWeatherReportDetailsShouldHaveTemperatureUnits() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails().getTemperatureUnit()).isNotNull();
    }

    @Test //Checks if current weather report section has date
    public void inAppCurrentWeatherReportShouldHaveDate() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getDate()).isNotNull();
    }

    @Test //Checks if current weather report section has temperature
    public void inAppCurrentWeatherReportShouldHaveTemperature() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getTemperature()).isNotNull();
    }

    @Test //Checks if current weather report section has humidity
    public void inAppCurrentWeatherReportShouldHaveHumidity() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getHumidity()).isNotNull();
    }

    @Test //Checks if current weather report section has pressure
    public void inAppCurrentWeatherReportShouldHavePressure() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getPressure()).isNotNull();
    }

    //----------------------- ITEMS FORMAT AND CONTENT TESTS -----------------------//

    @Test //Checks if coordinates in weather report section are in format "Lat,Lon"
    public void inAppWeatherReportCoordinatesInFormat_LatLon() throws CityNotFoundException {
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getWeatherReportDetails().getCoordinates()).
                isEqualTo(currentWeatherData.getCoord().getLat() + "," + currentWeatherData.getCoord().getLon());
    }

    @Test //Checks if temperature units in weather report section comply with measurement system set up in weather API
    public void inAppWeatherReportTemperatureUnitsCorrespondToApiSettings() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        //Implemented test with if, as separate tests will rise case when 1 test always will have error.
        if (weatherApi.getUnits().equals("metric")) {
            assertThat(weatherReport.getWeatherReportDetails().getTemperatureUnit()).isEqualTo("Celsius");
        } else if (weatherApi.getUnits().equals("imperial")) {
            assertThat(weatherReport.getWeatherReportDetails().getTemperatureUnit()).isEqualTo("Fahrenheit");
        }
    }

    @Test //Checks if date in current weather report in format "yyyy-MM-dd"
    public void inAppWeatherReportDateInFormat_yyyy_MM_dd() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getDate()).matches("\\d{4}-\\d{2}-\\d{2}");
    }

    @Test //Checks if date in current weather report is CURRENT
    public void inAppWeatherReportDateIsCurrentDate() throws CityNotFoundException {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getCurrentWeatherReport().getDate()).matches(dateFormat.format(currentDate));
    }


}
