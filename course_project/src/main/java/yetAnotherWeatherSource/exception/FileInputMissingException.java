package yetAnotherWeatherSource.exception;

public class FileInputMissingException extends Exception {
    public FileInputMissingException() { super ("No file specified for File Reader."); }
}
