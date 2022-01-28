package by.epam.javatraining.restautant.exception;

public class UtilException extends Exception {

    public UtilException() {
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(Exception e) {
        super(e);
    }

    public UtilException(String message, Exception e) {
        super(message, e);
    }
}
