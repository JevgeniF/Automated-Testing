package weatherBall.appFunctionality;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weatherBall.CLInterface;
import weatherBall.WeatherBall;
import weatherBall.api.WeatherApi;
import weatherBall.exception.CityNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    public void interfaceShouldOutputWeatherReportToConsoleIfArgsConsoleAndCityGiven() throws CityNotFoundException {
        String cityAsString = "Alabama";
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", cityAsString});

        assertThat(outputStream.toString().trim())
                .isEqualTo(weatherBall.getWeatherReport("Alabama").toString());

    }

    @Test
    public void interfaceShouldOutputCityNotFoundToConsoleIfArgsConsoleAndCityGiven() throws CityNotFoundException {
        String cityAsString = "Muhosransk";
        String errorMessage = String.format("%s city not found!", cityAsString);
        System.setOut(printStream);
        CLInterface.main(new String[]{"-console", cityAsString});

        assertThat(outputStream.toString().trim())
                .isEqualTo(errorMessage);

    }

}
