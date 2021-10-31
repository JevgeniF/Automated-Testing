package ee.icd0004.weatherBallPractice;

import ee.icd0004.weatherBallPractice.api.CurrentWeatherData;
import ee.icd0004.weatherBallPractice.api.WeatherApi;
import ee.icd0004.weatherBallPractice.exception.CityNotFoundException;

public class WeatherBall {

    private final WeatherApi weatherApi;

    public WeatherBall(WeatherApi weatherApi) { this.weatherApi = weatherApi; }

    public WeatherReport getWeatherReportForCity(String city) throws CityNotFoundException {
        WeatherReport weatherReport = new WeatherReport();
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);

        MainDetails mainDetails = new MainDetails();
        mainDetails.setCity(currentWeatherData.getName());

        weatherReport.setMainDetails(mainDetails);
        return weatherReport;

    }
}
