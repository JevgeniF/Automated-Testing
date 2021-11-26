package weatherBall.exception;

/**
 * Exception class.
 * Contains Exception for case when file specified as parameter for InOut class method getCityFromFile not txt.
 */
public class WrongInputFormatException extends Exception {
    public WrongInputFormatException() {
        super("Wrong file format. Should be txt file.");
    }
}
