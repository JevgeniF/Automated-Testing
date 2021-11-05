package weatherBall.model;

import lombok.Data;

/**
 * Class holds current weather data used for attribute weather in ForecastReport class.
 * Attributes:
 * temperature - current temperature
 * humidity - current humidity
 * pressure - current atmosphere pressure
 */
@Data
public class Weather {
    private Double temperature;
    private Integer humidity;
    private Integer pressure;

    // Setter method for attribute temperature, rounds parameter temperature to every half of unit.
    public void setTemperature(Double temperature) {
        this.temperature = Math.round(temperature * 2) / 2.0;
    }

    public String toString() {
        return "temperature: " + this.temperature + "\n" +
                "humidity: " + this.humidity + "\n" +
                "pressure: " + this.pressure + "\n";
    }
}
