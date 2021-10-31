package ee.icd0004.weatherBallPractice.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import ee.icd0004.weatherBallPractice.exception.CityNotFoundException;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class WeatherApi {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5";
    private static final String API_KEY = "8e648a10ee12b0e82dd59dab605e8db4";
    private static final String UNITS = "metric";


    public CurrentWeatherData getCurrentWeatherData(String city) throws CityNotFoundException {
        Client client = getConfiguredClient();
        String resourceUrl = BASE_URL + "/weather";

        ClientResponse response = client.resource(resourceUrl)
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", UNITS)
                .get(ClientResponse.class);

        if(response.getStatus() == HTTP_NOT_FOUND) {
            throw new CityNotFoundException();
        }

        return response.getEntity(CurrentWeatherData.class);
    }

    private static Client getConfiguredClient() {
        ClientConfig configuration = new DefaultClientConfig();
        configuration.getClasses().add(JacksonJaxbJsonProvider.class);
        configuration.getFeatures().put(FEATURE_POJO_MAPPING, true);
        return create(configuration);
    }
}
