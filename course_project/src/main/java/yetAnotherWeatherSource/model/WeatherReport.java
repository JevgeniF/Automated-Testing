package yetAnotherWeatherSource.model;

import lombok.Data;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for generation of entity from application output (JSON object).
 * Attributes:
 * weatherReportDetails - entity of WeatherReportDetails class, holds city name, coordinates, temperature unit
 * currentWeatherReport - entity of CurrentWeatherReport class, holds date, temperature, pressure, humidity
 */
@Data
public class WeatherReport {
    private WeatherReportDetails weatherReportDetails;
    private CurrentWeatherReport currentWeatherReport;
    private ArrayList<ForecastReport> forecastReport;

    /**
     * Method saves class entity as .json file
     */
    @SuppressWarnings("unused")
    public void toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("./Weather in " + weatherReportDetails.getCity() + ".json"),
                    this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
