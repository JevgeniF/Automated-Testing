package weatherBall.exception;

/**
 * Exception class.
 * Contains Exception for case when file given as parameter found by name in InOut class method getCityFromFile.
 */
public class FileNotFoundException extends Exception {
    public FileNotFoundException() {
        super("File not found.");
    }
}
