package yetAnotherWeatherSource.model;

import lombok.Data;

import java.util.Date;

@Data
public class CurrentWeather {
    private Date date;
    private Double temperature;
    private Integer humidity;
    private Integer pressure;
}
