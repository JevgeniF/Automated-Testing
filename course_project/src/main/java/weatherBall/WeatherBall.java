package weatherBall;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import weatherBall.api.WeatherApi;
import weatherBall.api.dto.MainDto;
import weatherBall.api.response.CurrentWeatherData;
import weatherBall.api.response.ForecastData;
import weatherBall.exception.CityNotFoundException;
import weatherBall.helpers.Helpers;
import weatherBall.model.CurrentWeatherReport;
import weatherBall.model.ForecastReport;
import weatherBall.model.WeatherReport;
import weatherBall.model.WeatherReportDetails;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class with main methods of application.
 * Generates in app weather report from API response.
 * Attributes:
 * weatherApi - entity of WeatherApi class with main attributes and methods of API
 */
@SuppressWarnings("ClassCanBeRecord")
public class WeatherBall {

    static Logger yawsLogger = (Logger) LoggerFactory.getLogger("weatherBall");

    private final WeatherApi weatherApi;

    public WeatherBall(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public WeatherReport getWeatherReport(String city) throws CityNotFoundException {
        WeatherReport weatherReport = new WeatherReport();
        CurrentWeatherData currentWeatherData = weatherApi.getCurrentWeatherData(city);
        ForecastData forecastData = weatherApi.getForecastData(city);

        WeatherReportDetails weatherReportDetails = getWeatherReportDetails(currentWeatherData);
        CurrentWeatherReport currentWeatherReport = getCurrentWeatherReport(currentWeatherData);
        ArrayList<ForecastReport> forecastReportList = getForecastReportList(forecastData);

        weatherReport.setWeatherReportDetails(weatherReportDetails);
        weatherReport.setCurrentWeatherReport(currentWeatherReport);
        weatherReport.setForecastReport(forecastReportList);

        return weatherReport;
    }

    //overloaded method for batch reports generation
    public ArrayList<WeatherReport> getWeatherReport(ArrayList<String> cityList) {
        ArrayList<WeatherReport> weatherReportList = new ArrayList<>();
        for (String city : cityList) {
            try {
                WeatherReport weatherReport = getWeatherReport(city);
                weatherReportList.add(weatherReport);
            } catch (CityNotFoundException e) {
                yawsLogger.error("Exception occurred when tried to get weather report for all cities:\n{}", new CityNotFoundException().getMessage());
            }
        }
        yawsLogger.info("Overloaded method used to get Weather Report List");
        return weatherReportList;
    }

    private WeatherReportDetails getWeatherReportDetails(CurrentWeatherData data) {
        WeatherReportDetails weatherReportDetails = new WeatherReportDetails();

        weatherReportDetails.setCity(data.getName());
        weatherReportDetails.setCoordinates(data.getCoord());
        weatherReportDetails.setTemperatureUnit(weatherApi.getUnits());

        return weatherReportDetails;
    }

    private CurrentWeatherReport getCurrentWeatherReport(CurrentWeatherData data) {
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport();

        currentWeatherReport.setDate(data.getDt());
        currentWeatherReport.setTemperature(data.getMain().getTemp());
        currentWeatherReport.setHumidity(data.getMain().getHumidity());
        currentWeatherReport.setPressure(data.getMain().getPressure());

        return currentWeatherReport;
    }

    private ArrayList<ForecastReport> getForecastReportList(ForecastData data) {
        ArrayList<ForecastReport> forecastReportList = new ArrayList<>();
        Map<String, ArrayList<MainDto>> forecastWeatherMap = Helpers.getForecastWeatherMap(data);

        forecastWeatherMap.forEach((key, value) -> {
            ForecastReport forecastReport = new ForecastReport();
            forecastReport.setDate(key);
            forecastReport.setWeather(Helpers.getAverageForecastWeather(value));
            forecastReportList.add(forecastReport);
        });

        return forecastReportList;
    }
}
