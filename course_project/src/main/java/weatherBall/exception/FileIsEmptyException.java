package weatherBall.exception;

public class FileIsEmptyException extends Exception {
    public FileIsEmptyException() {
        super("File is empty.");
    }
}
