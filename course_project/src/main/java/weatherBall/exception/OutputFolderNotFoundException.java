package weatherBall.exception;

public class OutputFolderNotFoundException extends Exception {
    public OutputFolderNotFoundException() { super("Output folder does not exists."); }
}
