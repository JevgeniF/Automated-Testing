package yetAnotherWeatherSource.model;

import lombok.Data;
import yetAnotherWeatherSource.api.dto.CoordDto;

/**
 * Class holds common weather report detalis used for attribute weatherReportDetails in WeatherReport class.
 * Attributes:
 * city - name of city
 * coordinates - coordinates of city in format Lat,Lon
 * temperatureUnit - temperature unit in accordance with measurement system set up in WeatherApi (default is Celsius)
 */
@Data
public class WeatherReportDetails {
    private String city;
    private String coordinates;
    private String temperatureUnit = "Celsius";

    /**
     * Setter for attribute coordinates.
     * Convert coordinates as entity of CoordDto into coordinates as String in Lat,Lon format.
     *
     * @param coord coordinates as CoordDto class entity.
     */
    public void setCoordinates(CoordDto coord) {
        this.coordinates = coord.getLat() + "," + coord.getLon();
    }

    /**
     * Setter for attribute temperatureUnit.
     * Set temperature units in accordance with measurement system set up in API, if system switched to Imperial.
     * Default units - Celsius.
     *
     * @param apiUnits - measurement system indicated in API (Attribute UNITS)
     */
    public void setTemperatureUnit(String apiUnits) {
        if (apiUnits != null) {
            if (apiUnits.equals("imperial")) this.temperatureUnit = "Fahrenheit";
        }
    }
}
