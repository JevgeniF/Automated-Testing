package weatherBall.model;

import lombok.Data;

/**
 * Class holds forecast report data used for attribute in WeatherReport class.
 * Attributes:
 * date - forecast date in format yyyy-MM-dd
 * weather - entity of Weather class, holds date, temperature, pressure, humidity for date in forecast
 */
@Data
public class ForecastReport {
    private String date;
    private Weather weather;
}
