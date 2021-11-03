package yetAnotherWeatherSource.exception;

/**
 * Exception class.
 * Contains Exception for case when file not specified as parameter for InOut class method getCityFromFile.
 */
public class FileInputMissingException extends Exception {
    public FileInputMissingException() {
        super("No file specified for File Reader.");
    }
}
