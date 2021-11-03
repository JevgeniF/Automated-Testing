package yetAnotherWeatherSourse.mockIntegration;

import org.junit.Before;
import org.mockito.Mock;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.dto.CoordDto;
import yetAnotherWeatherSource.api.dto.ListDto;
import yetAnotherWeatherSource.api.dto.MainDto;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;
import yetAnotherWeatherSource.api.response.ForecastData;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class MockIntegrationTests {
    @Mock
    static WeatherApi weatherApi;
    protected String city;
    protected CurrentWeatherData currentWeatherData;
    protected ForecastData forecastData;
    protected YetAnotherWeatherSource yetAnotherWeatherSource;

    @Before //initial setup to avoid code repeats, as used in all tests.
    public void setUp() {
        city = "Haabneeme";

        Integer currentDate = Math.toIntExact(System.currentTimeMillis() / 1000L);
        CoordDto coordinates = new CoordDto(24.8211, 59.5114);
        MainDto currentWeather = new MainDto(8.74, 1010, 75);

        Date tomorrow = Date.from(Instant.now().plus(Duration.ofDays(1)));
        int tomorrowInUnix = Math.toIntExact(tomorrow.getTime() / 1000);
        MainDto forecastWeather = new MainDto(5.56, 1007, 92);

        currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setName(city);
        currentWeatherData.setCoord(coordinates);
        currentWeatherData.setDt(currentDate);
        currentWeatherData.setMain(currentWeather);

        forecastData = new ForecastData();

        ArrayList<ListDto> listList = new ArrayList<>();
        ListDto list = new ListDto();
        list.setDt(Math.toIntExact(tomorrowInUnix));
        list.setMain(forecastWeather);
        listList.add(list);

        forecastData.setList(listList);

        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }
}
