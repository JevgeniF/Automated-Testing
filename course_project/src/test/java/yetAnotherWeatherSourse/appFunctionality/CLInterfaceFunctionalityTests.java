package yetAnotherWeatherSourse.appFunctionality;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yetAnotherWeatherSource.CLInterface;
import yetAnotherWeatherSource.YetAnotherWeatherSource;
import yetAnotherWeatherSource.api.WeatherApi;
import yetAnotherWeatherSource.exception.CityNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CLInterfaceFunctionalityTests {

    private PrintStream printStream;
    public static YetAnotherWeatherSource yetAnotherWeatherSource;
    private PrintStream oldPrintStream;
    private ByteArrayOutputStream outputStream;

    @BeforeAll
    static void setUp() {
        WeatherApi weatherApi = new WeatherApi();
        yetAnotherWeatherSource = new YetAnotherWeatherSource(weatherApi);
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
        CLInterface.main(new String[] {"-console", cityAsString});

        assertThat(outputStream.toString().trim())
                .isEqualTo(yetAnotherWeatherSource.getWeatherReport("Alabama").toString());

    }
}
