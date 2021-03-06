package weatherBall.model;

import lombok.Data;
import weatherBall.api.dto.CoordDto;

/**
 * Class holds common weather report details used for attribute weatherReportDetails in WeatherReport class.
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

    public void setCoordinates(CoordDto coord) {
        this.coordinates = coord.getLat() + "," + coord.getLon();
    }

    public void setTemperatureUnit(String apiUnits) {
        if (apiUnits != null) {
            if (apiUnits.equals("imperial")) this.temperatureUnit = "Fahrenheit";
        }
    }

    public String toString() {
        return "------- Weather Report Details -------\n" +
                "\t\t\tcity: " + this.city + "\n" +
                "coordinates: " + this.coordinates + "\n" +
                "temperatureUnit: " + this.temperatureUnit + "\n";
    }
}
