package weatherBall.appFunctionality;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weatherBall.CLInterface;
import weatherBall.WeatherBall;
import weatherBall.api.WeatherApi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CLInterfaceFunctionalityTests {

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
    public void interfaceShouldOutputWeatherReportWithCelsiusToConsoleIfArgsCConsoleAndCityGiven() {
        String cityAsString = "Alabama";
        System.setOut(printStream);
        CLInterface.main(new String[]{"-c", "-console", cityAsString});

        assertTrue(outputStream.toString().trim().contains("Celsius"));

    }

    @Test
    @SneakyThrows
    public void interfaceShouldOutputWeatherReportWithFahrenheitToConsoleIfArgsFConsoleAndCityGiven() {
        String cityAsString = "Alabama";
        System.setOut(printStream);
        CLInterface.main(new String[]{"-f", "-console", cityAsString});

        assertTrue(outputStream.toString().trim().contains("Celsius"));

    }

}
