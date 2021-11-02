package yetAnotherWeatherSource.model;

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

    /**
     * Setter method for attribute date, converts date from UnixMills to String in format yyyy-MM-dd.
     *
     * @param unixDate - date in UnixMills as Integer
     */
    public void setDate(Integer unixDate) {
        Date date = new Date(unixDate * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.date = dateFormat.format(date);
    }

    /**
     * Setter method for attribute temperature, rounds parameter temperature to every half of unit.
     *
     * @param temperature - raw unformatted temperature as Double
     */
    public void setTemperature(Double temperature) {
        this.temperature = Math.round(temperature * 2) / 2.0;
    }
}
