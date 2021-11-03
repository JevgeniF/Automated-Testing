package yetAnotherWeatherSource.model;

import lombok.Data;

@Data
public class Weather {
    private Double temperature;
    private Integer humidity;
    private Integer pressure;

    /**
     * Setter method for attribute temperature, rounds parameter temperature to every half of unit.
     *
     * @param temperature - raw unformatted temperature as Double
     */
    public void setTemperature(Double temperature) {
        this.temperature = Math.round(temperature * 2) / 2.0;
    }
}
