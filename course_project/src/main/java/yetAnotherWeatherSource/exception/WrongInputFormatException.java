package yetAnotherWeatherSource.exception;

public class WrongInputFormatException extends Exception {
    public WrongInputFormatException() {
        super("Wrong file format. Should be txt file.");
    }
}
