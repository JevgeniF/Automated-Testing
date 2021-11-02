package yetAnotherWeatherSourse.mockIntegration;

import org.junit.Before;
import org.mockito.Mock;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.api.dto.CoordDto;
import yetAnotherWeatherSource.api.dto.MainDto;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;

public class MockIntegrationTests {
    @Mock
    static WeatherApi weatherApi;
    protected String city;
    protected CurrentWeatherData currentWeatherData;
    protected YetAnotherWeatherSource yetAnotherWeatherSource;

    @Before //initial setup to avoid code repeats, as used in all tests.
    public void setUp() {
        city = "Haabneeme";
        Integer date = 1635846093;
        CoordDto coordinates = new CoordDto(24.8211, 59.5114);
        MainDto currentWeather = new MainDto(8.74, 1010, 75);

        currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setName(city);
        currentWeatherData.setCoord(coordinates);
        currentWeatherData.setDt(date);
        currentWeatherData.setMain(currentWeather);

        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
    }
}
