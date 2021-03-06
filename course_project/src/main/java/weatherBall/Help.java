package weatherBall;

public class Help {
    public static final String help =
            """
                    This is WeatherBall version 0.1-NIGHTLY

                    WeatherBall generates Weather Forecasts with Current Weather and Next 3 Days Weather using OpenWeatherMap API.

                    To get weather report, input in console:
                        java -jar [WeatherBall package name].jar [outputMode] [not necessary:units] [input] [not necessary:outputFolder]

                    with attributes. App accepts up to 4 attributes in a row.
                    Not necessary attributes may be skipped.

                    [outputMode] may have the following parameters:
                        -console        Displays weather reports on the console.
                        -json           Saves weather reports to JSON file (Separately for each city).

                    [not necessary:units] may have the following parameters:
                        -c              Set up temperature units for reports to Celsius.
                        -f              Set up temperature units for reports to Fahrenheit.
                                        !!! If skipped. App uses Celsius by default. !!!

                    [input] may have the following parameters:
                        city            Input any city name. Suitable for quick report generation for one city from console line
                        fileName        Input path and name of txt file, containing one city per one line for batch weather reports
                                        generation.
                                        !!! In case of file input, cities with wrong names will be skipped. !!!

                    [not necessary:outputFolder] may have the following parameters
                        path        Changes location, where JSON file(s) will be saved.
                                        !!! If skipped. Saves JSON file(s) to app folder.

                    Examples of proper startup from console:
                        java -jar WeatherBall.jar -console Rome
                        java -jar WeatherBall.jar -console -f Rome
                        java -jar WeatherBall.jar -json cities.txt
                        java -jar WeatherBall.jar -json -f cities.txt
                        java -jar WeatherBall.jar -json cities.txt ./new_location/
                        java -jar WeatherBall.jar -json -f cities.txt ./new_location/""";
}
