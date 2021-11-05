package weatherBall.appFunctionality;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weatherBall.CLInterface;
import weatherBall.WeatherBall;
import weatherBall.api.WeatherApi;
import weatherBall.inOut.InOut;
import weatherBall.model.WeatherReport;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CLInterfaceFunctionalityTests {

    private static final String INPUT_DATA_FOLDER = "./src/main/resources/test_data/input/";
    private static final String OUTPUT_DATA_FOLDER = "./src/main/resources/test_data/output/";
    public static WeatherBall weatherBall;
    private PrintStream printStream;
    private PrintStream oldPrintStream;
    private ByteArrayOutputStream outputStream;

    @BeforeAll
    static void setUp() {
        WeatherApi weatherApi = new WeatherApi();
        weatherBall = new WeatherBall(weatherApi);
    }

    @BeforeEach
    void streamSetUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        oldPrintStream = System.out;
    }

    @AfterEach
    void streamRestore() {
        System.out.flush();
        System.setOut(oldPrintStream);
    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputWeatherReportToConsoleIfArgsConsoleAndCityGiven() {
        String cityAsString = "Alabama";
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", cityAsString});

        assertThat(outputStream.toString().trim())
                .isEqualTo(weatherBall.getWeatherReport("Alabama").toString());

    }

    @Test
    public void interfaceShouldOutputCityNotFoundToConsoleIfWrongCityGiven() {
        String cityAsString = "Muhosransk";
        String errorMessage = String.format("%s city not found!", cityAsString);
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", cityAsString});

        assertThat(outputStream.toString().trim())
                .isEqualTo(errorMessage);

    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputWeatherReportWithCelsiusToConsoleIfArgsConsoleCAndCityGiven() {
        String cityAsString = "Alabama";
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", "-c", cityAsString});

        assertTrue(outputStream.toString().trim().contains("Celsius"));

    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputWeatherReportWithFahrenheitToConsoleIfArgsConsoleFAndCityGiven() {
        String cityAsString = "Alabama";
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", "-f", cityAsString});
        assertTrue(outputStream.toString().trim().contains("Fahrenheit"));
    }

    @Test
    @SneakyThrows
    public void interfaceShouldSaveToJsonIfArgsJsonCityAndOutputPathGiven() {
        String cityAsString = "Alabama";
        CLInterface.main(new String[]{"-json", cityAsString, OUTPUT_DATA_FOLDER});

        File outputFile = new File(OUTPUT_DATA_FOLDER + "Weather in Alabama.json");
        assertTrue(outputFile.exists() && outputFile.getName().contains("Alabama"));
    }

    @Test
    public void interfaceShouldOutputCityNotFoundToConsoleIfWrongCityGivenWithFirstArgJson() {
        String cityAsString = "Muhosransk";
        String errorMessage = String.format("%s city not found!", cityAsString);
        System.setOut(printStream);
        CLInterface.main(new String[]{"-json", cityAsString, OUTPUT_DATA_FOLDER});

        assertThat(outputStream.toString().trim())
                .isEqualTo(errorMessage);

    }

    @Test
    @SneakyThrows
    public void interfaceShouldSaveToJsonInDefaultFolderIfArgsHasNoPath() {
        String cityAsString = "Alabama";
        CLInterface.main(new String[]{"-json", cityAsString});

        File outputFile = new File("Weather in Alabama.json");
        assertTrue(outputFile.exists() && outputFile.getName().contains("Alabama"));
    }

    @Test
    @SneakyThrows
    public void interfaceShouldSaveJsonWithCelsiusIfArgsConsoleCAndCityGiven() {
        String cityAsString = "Alabama";
        CLInterface.main(new String[]{"-json", "-c", cityAsString, OUTPUT_DATA_FOLDER});

        File outputFile = new File(OUTPUT_DATA_FOLDER + "Weather in Alabama.json");

        try {
            Scanner scanner = new Scanner(outputFile);
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                assertTrue(line.contains("Celsius"));
            }
        } catch (FileNotFoundException e) {
            fail();
        }
    }

    @Test
    @SneakyThrows
    public void interfaceShouldSaveJsonWithFahrenheitIfArgsConsoleFAndCityGiven() {
        String cityAsString = "Alabama";
        CLInterface.main(new String[]{"-json", "-c", cityAsString, OUTPUT_DATA_FOLDER});

        File outputFile = new File(OUTPUT_DATA_FOLDER + "Weather in Alabama.json");

        try {
            Scanner scanner = new Scanner(outputFile);
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                assertTrue(line.contains("Celsius"));
            }
        } catch (FileNotFoundException e) {
            fail();
        }
    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputWeatherReportToConsoleIfArgsConsoleAndFileGiven() {
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", INPUT_DATA_FOLDER + "cities.txt"});

        ArrayList<String> cityList = InOut.getCitiesFromFile(INPUT_DATA_FOLDER + "cities.txt");
        ArrayList<WeatherReport> weatherReportList = weatherBall.getWeatherReport(cityList);
        StringBuilder reports = new StringBuilder();
        for (WeatherReport weatherReport : weatherReportList) {
            reports.append(weatherReport).append("\n");
        }
        assertThat(outputStream.toString().trim())
                .isEqualTo(reports.toString());

    }
}
