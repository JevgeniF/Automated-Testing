package yetAnotherWeatherSource.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ForecastReport {
    private String date;
    private ForecastWeather forecastWeather;

    public void setDate(Integer unixDate) {
        Date date = new Date(unixDate * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.date = dateFormat.format(date);
    }
}
