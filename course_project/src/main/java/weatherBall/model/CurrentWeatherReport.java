package weatherBall.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class holds current weather data used for attribute currentWeatherReport in WeatherReport class.
 * Attributes:
 * date - current date in format yyyy-MM-dd
 * temperature - current temperature
 * humidity - current humidity
 * pressure - current atmosphere pressure
 */
@Data
public class CurrentWeatherReport {
    private String date;
    private Double temperature;
    private Integer humidity;
    private Integer pressure;

    public void setDate(Long unixDate) {
        Date date = new Date(unixDate * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.date = dateFormat.format(date);
    }

    // Setter method for attribute temperature, rounds parameter temperature to every half of unit.
    public void setTemperature(Double temperature) {
        this.temperature = Math.round(temperature * 2) / 2.0;
    }

    public String toString() {
        return "------ Current Weather Report --------\n" +
                "\t\t  date: " + this.date + "\n" +
                "temperature: " + this.temperature + "\n" +
                "humidity: " + this.humidity + "\n" +
                "pressure: " + this.pressure + "\n";
    }
}
