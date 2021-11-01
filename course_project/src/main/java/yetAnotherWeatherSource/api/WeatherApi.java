package yetAnotherWeatherSource.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import yetAnotherWeatherSource.api.response.CurrentWeatherData;

import static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;

public class WeatherApi {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5";
    private static final String CURRENT_WEATHER_URL = BASE_URL + "/weather";
    private static final String API_KEY = "8e648a10ee12b0e82dd59dab605e8db4";
    private static final String UNITS = "metric";

    private static final Client client = getConfiguredClient();

    public static CurrentWeatherData getCurrentWeatherData(String city) {
        return getCurrentWeatherResponse(city).getEntity(CurrentWeatherData.class);
    }

    public static ClientResponse getCurrentWeatherResponse(String city) {
        return client.resource(CURRENT_WEATHER_URL)
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
}
