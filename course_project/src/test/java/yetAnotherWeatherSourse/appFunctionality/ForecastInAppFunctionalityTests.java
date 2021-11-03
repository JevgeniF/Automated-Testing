package yetAnotherWeatherSourse.appFunctionality;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.dto.MainDto;
import yetAnotherWeatherSource.api.exception.CityNotFoundException;
import yetAnotherWeatherSource.model.Weather;
import yetAnotherWeatherSource.model.WeatherReport;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForecastInAppFunctionalityTests {
    private static String city;
    private static YetAnotherWeatherSource yetAnotherWeatherSource;

    @BeforeAll // initial setup for all tests
    static void setUp() {
        city = "Florence";
        WeatherApi weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }

    //----------------------- STRUCTURE TESTS -----------------------//

    @Test //Checks if weather report has section with forecast report
    public void inAppWeatherReportShouldHaveSectionForecastReport() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport()).isNotNull();
    }

    @Test //Checks if forecast report section in weather report has at least one "day"
    public void inAppWeatherReportShouldHaveSectionForecastReportWithMinOneDay()
            throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0)).isNotNull();
    }

    @Test //Checks if forecast report section has at least one "day" with weather forecast
    public void inAppForecastReportSectionShouldHaveMinOneDayWithWeatherForecast()
            throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getWeather()).isNotNull();
    }

    //----------------------- ITEMS EXISTENCE TESTS -----------------------//

    @Test //Checks if forecast report section "day" has date
    public void inAppForecastReportSectionShouldHaveMinOneDayWithDate() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getDate()).isNotNull();
    }

    @Test //Checks if forecast report section "day" weather forecast has temperature
    public void inAppForecastReportDayShouldHaveWeatherForecastWithTemperature() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getWeather().getTemperature()).isNotNull();
    }

    @Test //Checks if forecast report section "day" weather forecast has humidity
    public void inAppForecastReportDayShouldHaveWeatherForecastWithHumidity() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getWeather().getHumidity()).isNotNull();
    }

    @Test //Checks if forecast report section "day" weather forecast has pressure
    public void inAppForecastReportDayShouldHaveWeatherForecastWithPressure() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getWeather().getPressure()).isNotNull();
    }

    //----------------------- ITEMS FORMAT AND CONTENT TESTS -----------------------//

    @Test //Checks if forecast has 3 days
    public void inAppDaysInForecastReportShouldHave3Days() throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertEquals(weatherReport.getForecastReport().size(), 3);
    }

    @Test //Checks if forecast days in Consecutive Ascending Order.
    public void inAppDaysInForecastReportShouldBeConsecutiveAndInAscendingOrder() throws CityNotFoundException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tomorrow = dateFormat.format(Date.from(Instant.now().plus(Duration.ofDays(1))));
        String dayAfterTomorrow = dateFormat.format(Date.from(Instant.now().plus(Duration.ofDays(2))));
        String dayAfterTomorrowPlusDay = dateFormat.format(Date.from(Instant.now().plus(Duration.ofDays(3))));

        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);

        assertThat(weatherReport.getForecastReport().get(0).getDate()).matches(tomorrow);
        assertThat(weatherReport.getForecastReport().get(1).getDate()).matches(dayAfterTomorrow);
        assertThat(weatherReport.getForecastReport().get(2).getDate()).matches(dayAfterTomorrowPlusDay);
    }

    @Test //Checks if dates in forecast report "days" formatted like "yyyy-MM-dd"
    public void inAppDaysInForecastReportSectionHaveDatesInFormat_yyyy_MM_dd()
            throws CityNotFoundException {
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getDate()).matches("\\d{4}-\\d{2}-\\d{2}");
    }

    @Test //Checks if first "day" in Forecast Report is tomorrow day
    public void inAppForecastReportSectionFirstDayShouldBeTomorrow() throws CityNotFoundException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tomorrow = dateFormat.format(Date.from(Instant.now().plus(Duration.ofDays(1))));
        WeatherReport weatherReport = yetAnotherWeatherSource.getWeatherReport(city);
        assertThat(weatherReport.getForecastReport().get(0).getDate()).matches(tomorrow);
    }

    @Test /* Checks if main app class calculates average temperature for each date in forecast properly.
          Test checks purely getAverageWeather method of YetAnotherWeatherSource class without data fetched from API. */
    public void inAppMethodCalculatesAverageTemperatureForEachDayInForecast() {
        ArrayList<MainDto> artificialMainDtoList = getArtificialMainDtoList();
        Weather weather = yetAnotherWeatherSource.getAverageForecastWeather(artificialMainDtoList);
        assertThat(weather.getTemperature()).isEqualTo(0.5);
    }

    @Test /* Checks if main app class calculates average pressure for each date in forecast properly.
          Test checks purely getAverageWeather method of YetAnotherWeatherSource class without data fetched from API. */
    public void inAppMethodCalculatesAveragePressureForEachDayInForecast() {
        ArrayList<MainDto> artificialMainDtoList = getArtificialMainDtoList();
        Weather weather = yetAnotherWeatherSource.getAverageForecastWeather(artificialMainDtoList);
        assertThat(weather.getPressure()).isEqualTo(200);
    }

    @Test /* Checks if main app class calculates average humidity for each date in forecast properly.
          Test checks purely getAverageWeather method of YetAnotherWeatherSource class without data fetched from API. */
    public void inAppMethodCalculatesAverageHumidityForEachDayInForecast() {
        ArrayList<MainDto> artificialMainDtoList = getArtificialMainDtoList();
        Weather weather = yetAnotherWeatherSource.getAverageForecastWeather(artificialMainDtoList);
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
