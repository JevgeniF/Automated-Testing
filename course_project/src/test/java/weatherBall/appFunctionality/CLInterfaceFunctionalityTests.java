package weatherBall.appFunctionality;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
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
import java.util.Objects;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CLInterfaceFunctionalityTests {

    private static final String INPUT_DATA_FOLDER = "./src/main/resources/test_data/input/";
    private static final String OUTPUT_DATA_FOLDER = "./src/main/resources/test_data/output/";
    public static WeatherBall weatherBall;
    String cityAsString = "Alabama";
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
    public void interfaceShouldOutputWeatherReportToConsoleIfArgsConsoleAndInputGiven() {
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", cityAsString});

        assertThat(outputStream.toString().trim())
                .isEqualTo(weatherBall.getWeatherReport("Alabama").toString());

    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputCityNotFoundToConsoleIfWrongInputGiven() {
        String cityAsString = "Muhosransk";
        String errorMessage = String.format("%s city not found!", cityAsString);
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", cityAsString});

        assertThat(outputStream.toString().trim())
                .isEqualTo(errorMessage);

    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputWeatherReportWithCelsiusToConsoleIfArgsConsoleCAndInputGiven() {
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", "-c", cityAsString});

        assertTrue(outputStream.toString().trim().contains("Celsius"));

    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputWeatherReportWithFahrenheitToConsoleIfArgsConsoleFAndInputGiven() {
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", "-f", cityAsString});
        assertTrue(outputStream.toString().trim().contains("Fahrenheit"));
    }

    @Test
    @SneakyThrows
    public void interfaceShouldSaveToJsonIfArgsJsonInputAndOutputPathGiven() {
        CLInterface.main(new String[]{"-json", cityAsString, OUTPUT_DATA_FOLDER});

        File outputFile = new File(OUTPUT_DATA_FOLDER + "Weather in Alabama.json");
        assertTrue(outputFile.exists() && outputFile.getName().contains("Alabama"));
    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputCityNotFoundToConsoleIfWrongInputGivenWithFirstArgJson() {
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
        CLInterface.main(new String[]{"-json", cityAsString});

        File outputFile = new File("Weather in Alabama.json");
        assertTrue(outputFile.exists() && outputFile.getName().contains("Alabama"));
    }

    @Test
    @SneakyThrows
    public void interfaceShouldSaveJsonWithCelsiusIfArgsConsoleCAndCityGiven() {
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
    public void interfaceShouldOutputWeatherReportToConsoleIfArgsConsoleAndInputFileGiven() {
        String fileName = INPUT_DATA_FOLDER + "cities.txt";

        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", fileName});

        ArrayList<String> cityList = InOut.getCitiesFromFile(fileName);
        ArrayList<WeatherReport> weatherReportList = weatherBall.getWeatherReport(cityList);
        StringBuilder reports = new StringBuilder();
        for (WeatherReport weatherReport : weatherReportList) {
            reports.append(weatherReport).append("\n");
        }
        assertThat(outputStream.toString())
                .isEqualTo(reports.toString());
    }

    @Test
    @SneakyThrows
    public void interfaceShowsErrorMessageWhenArgConsoleAndInputFileNotTxt() {
        String fileName = INPUT_DATA_FOLDER + "wrong_format.pdf";
        String errorMessage = "File has wrong format. Only txt allowed.";

        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", fileName});

        assertThat(outputStream.toString().trim())
                .isEqualTo(errorMessage);
    }

    @Test
    @SneakyThrows
    public void interfaceShowsErrorMessageWhenArgConsoleAndInputFileIsEmpty() {
        String fileName = INPUT_DATA_FOLDER + "empty_file.txt";
        String errorMessage = "File is empty.";

        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", fileName});

        assertThat(outputStream.toString().trim())
                .isEqualTo(errorMessage);
    }

    @Test
    @SneakyThrows
    public void interfaceShowsErrorMessageWhenArgConsoleAndMissingInput() {
        String fileName = "";
        String errorMessage = "Input not found.";

        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", fileName});

        assertThat(outputStream.toString().trim())
                .isEqualTo(errorMessage);
    }

    @Test
    @SneakyThrows
    public void interfaceShouldSaveWeatherReportsToJsonIfArgsJsonAndInputFileGiven() {
        String fileName = INPUT_DATA_FOLDER + "cities.txt";

        CLInterface.main(new String[]{"-json", fileName, OUTPUT_DATA_FOLDER + "batch/"});

        int jsonFileCount = Objects.requireNonNull(new File(OUTPUT_DATA_FOLDER + "batch/")
                .listFiles(file -> !file.isHidden())).length;

        Assertions.assertThat(jsonFileCount).isEqualTo(13);
    }
}
