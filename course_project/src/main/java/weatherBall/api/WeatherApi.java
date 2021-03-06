package weatherBall.api;

import ch.qos.logback.classic.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.slf4j.LoggerFactory;
import weatherBall.api.response.CurrentWeatherData;
import weatherBall.api.response.ForecastData;
import weatherBall.exception.CityNotFoundException;

import static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

/**
 * WeatherApi class for OpenWeatherMap.Com API.
 * Contains API set up and methods to get response from API and create instance of CurrentWeatherData Class.
 * <p>
 * Attributes:
 * BASE_URL    an url giving the base location of APIs
 * CURRENT_WEATHER_URL an url giving location of Current Weather API
 * FORECAST_URL an url giving location of Forecast API
 * API_KEY     a unique key of subscribed user, gives access to API functionality
 * UNITS       setting for API, changing measurement Units in API response
 * client      jersey client, configured to instantiate JAX-RS and map PlainOldJavaObjects
 */
public class WeatherApi {

    static final Logger apiLogger = (Logger) LoggerFactory.getLogger("api.weatherApi");
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5";
    private static final String CURRENT_WEATHER_URL = BASE_URL + "/weather";
    private static final String FORECAST_URL = BASE_URL + "/forecast";
    private static final String API_KEY = "8e648a10ee12b0e82dd59dab605e8db4";
    private static final Client client = getConfiguredClient();
    private static String UNITS = "metric";

    public static ClientResponse getCurrentWeatherResponse(String city) {
        return client.resource(CURRENT_WEATHER_URL)
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", UNITS)
                .get(ClientResponse.class);
    }

    public static ClientResponse getForecastResponse(String city) {
        return client.resource(FORECAST_URL)
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", UNITS)
                .get(ClientResponse.class);
    }

    private static Client getConfiguredClient() {
        ClientConfig configuration = new DefaultClientConfig();
        configuration.getClasses().add(JacksonJaxbJsonProvider.class);
        configuration.getFeatures().put(FEATURE_POJO_MAPPING, true);
        return create(configuration);
    }

    public String getUnits() {
        return UNITS;
    }

    public static void setUnits(String measurementSystem) {
        UNITS = measurementSystem;
    }

    public CurrentWeatherData getCurrentWeatherData(String city) throws CityNotFoundException {
        ClientResponse response = getCurrentWeatherResponse(city);

        if (response.getStatus() == HTTP_NOT_FOUND) {

            apiLogger.warn("Error occurred on sending current weather request to API: {}", HTTP_NOT_FOUND);
            throw new CityNotFoundException();
        }
        return response.getEntity(CurrentWeatherData.class);
    }

    public ForecastData getForecastData(String city) throws CityNotFoundException {
        ClientResponse response = getForecastResponse(city);

        if (response.getStatus() == HTTP_NOT_FOUND) {
            apiLogger.warn("Error occurred on sending forecast request to API: {}", HTTP_NOT_FOUND);

            throw new CityNotFoundException();
        }
        return response.getEntity(ForecastData.class);
    }
}
