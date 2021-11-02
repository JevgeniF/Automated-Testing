package yetAnotherWeatherSourse.appFunctionality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.model.WeatherReport;

import static org.assertj.core.api.Assertions.assertThat;

public class ForecastInAppFunctionalityTests {
    private static String city;
    private static WeatherApi weatherApi;
    private static YetAnotherWeatherSource yetAnotherWeatherSource;

    @BeforeAll // initial setup for all tests
    static void setUp() {
        city = "Florence";
        weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    //----------------------- STRUCTURE TESTS -----------------------//

    @Test //Checks if weather report has section with forecast report
    public void inAppWeatherReportShouldHaveSectionForecastReport() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport()).isNotNull();
    }

    @Test //Checks if forecast report section in weather report has at least one forecast weather block
    public void inAppWeatherReportShouldHaveSectionForecastReportWithMinOneDay()
            throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0)).isNotNull();
    }

    @Test //Checks if forecast report section has date
    public void inAppForecastReportSectionShouldHaveMinOneDayWithDate()
            throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getDate()).isNotNull();
    }
}
