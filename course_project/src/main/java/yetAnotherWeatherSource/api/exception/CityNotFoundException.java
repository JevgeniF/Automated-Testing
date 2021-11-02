package yetAnotherWeatherSource.api.exception;

/**
 * Exception class.
 * Contains Exception for HTTP_NOT_FOUND error, while wrong city parameter was passed to API.
 */
public class CityNotFoundException extends Exception {

    public CityNotFoundException() {
        super("City not found.");
    }
}
