package yetAnotherWeatherSource.model;

import lombok.Data;
import yetAnotherWeatherSource.api.model.CoordinatesModel;

@Data
public class WeatherReportDetails {
    private String city;
    private String coordinates;
    private String temperatureUnit = "Celsius";

    public void setCoordinates(CoordinatesModel coord) {
        this.coordinates = coord.getLat() + ", " + coord.getLon();
    }

    public void setTemperatureUnit(String apiUnits) {
        if (apiUnits != null) {
            if (apiUnits.equals("imperial")) this.temperatureUnit = "Fahrenheit";
        }
    }
}
