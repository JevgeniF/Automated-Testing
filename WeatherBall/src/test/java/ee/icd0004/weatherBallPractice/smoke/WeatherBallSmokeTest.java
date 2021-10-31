package ee.icd0004.weatherBallPractice.smoke;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class WeatherBallSmokeTest {

    /* private static WeatherBall weatherBall;

    @BeforeClass
    public static void setUp() {
        weatherBall = new WeatherBall(new WeatherApi());
    }*/

    private static final String APPID = "8e648a10ee12b0e82dd59dab605e8db4";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String CITY = "Pärnu";
    private static final String UNITS = "metric";

    @Test
    public void whenCalledWithoutApiKey_returnsHttpUnauthorized() {
        given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(HTTP_UNAUTHORIZED);
    }

    @Test
    public void shouldReturnHttpOk_whenCityNameIsGiven() {
        given()
                .queryParam("q", CITY)
                .queryParam("units", UNITS)
                .queryParam("appid", APPID)
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(HTTP_OK);

    }

    @Test
    public void shouldReturnCityName_whenCityNameIsGiven() {
        given()
                .queryParam("q", CITY)
                .queryParam("units", UNITS)
                .queryParam("appid", APPID)
                .when()
                .get(BASE_URL)
                .then()
                .body("name", equalTo(CITY));

    }

    @Test
    public void shouldHaveCoordinatesBlock_whenCityNameIsGiven() {
        given()
                .queryParam("q", CITY)
                .queryParam("units", UNITS)
                .queryParam("appid", APPID)
                .when()
                .get(BASE_URL)
                .then()
                .body("$" , hasKey("coord"))
                .body("coord", hasKey("lon"))
                .body("coord", hasKey("lat"));

    }

/*    @Test
    public void shouldHaveMainDetailsInWeatherReport() {
        String city = "Pärnu";
        WeatherReport weatherReport = weatherBall.getWeatherReportForCity(city);

        assertThat(weatherReport.getMainDetails()).isNotNull();
    }

    @Test
    public void shouldHaveCityNameInWeatherReport() {
        String city = "Pärnu";
        WeatherReport weatherReport = weatherBall.getWeatherReportForCity(city);

        assertThat(weatherReport.getMainDetails().getCity()).isEqualTo(city);
    }*/
}
