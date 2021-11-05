package weatherBall;

public class Help {
        public static final String help =
                "This is WeatherBall version 0.1-NIGHTLY\n" +
            "\n" +
            "WeatherBall generates Weather Forecasts with Current Weather and Next 3 Days Weather using OpenWeatherMap API.\n" +
            "\n" +
            "To get weather report input in console:\n" +
            "    java -jar [weather ball package name] [outputFormat] [not necessary:units] [input] [not necessary:outputFolder]\n" +
            "\n" +
            "with attributes. App accepts up to 4 attributes in a row.\n" +
            "Not necessary attributes may be skipped.\n" +
            "\n" +
            "[outputFormat] may have the following parameters:\n" +
            "    -console        Displays weather reports on the console.\n" +
            "    -json           Saves weather reports to JSON file (Separately for each city).\n" +
            "\n" +
            "[not necessary:units] may have the following parameters:\n" +
            "    -c              Set up temperature units for reports to Celsius.\n" +
            "    -f              Set up temperature units for reports to Fahrenheit.\n" +
            "                    !!! If skipped. App uses Celsius by default. !!!\n" +
            "\n" +
            "[input] may have the following parameters:\n" +
            "    city            Input any city name. Suitable for quick report generation for one city from console line\n" +
            "    fileName        Input path and name of txt file, containing one city per one line for batch weather reports\n" +
            "                    generation.\n" +
            "                    !!! In case of file input, cities with wrong names will be skipped. !!!\n" +
            "\n" +
            "[not necessary:outputFolder] may have the following parameters\n" +
            "    filePath        Changes location, where JSON file(s) will be saved.\n" +
            "                    !!! If skipped. Saves JSON file(s) to app folder.\n" +
            "\n" +
            "Examples of proper startup from console:\n" +
            "    java -jar WeatherBall.jar -console Rome\n" +
            "    java -jar WeatherBall.jar -console -f Rome\n" +
            "    java -jar WeatherBall.jar -json cities.txt\n" +
            "    java -jar WeatherBall.jar -json -f cities.txt\n" +
            "    java -jar WeatherBall.jar -json cities.txt ./new_location/\n" +
            "    java -jar WeatherBall.jar -json -f cities.txt ./new_location/";
}
