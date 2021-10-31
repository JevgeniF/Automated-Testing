package ee.icd0004.weatherBallPractice.exception;

public class CityNotFoundException extends Exception {

    public CityNotFoundException() {
        super("Provided city name not found.");
    }
}
