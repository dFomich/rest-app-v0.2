package by.epam.javatraining.restautant.exception;

public class PositionInitializeException extends Exception {

    public PositionInitializeException() {
    }

    public PositionInitializeException(String message) {
        super(message);
    }

    public PositionInitializeException(String message, Exception e) {
        super(message, e);
    }

    public PositionInitializeException(Exception e) {
        super(e);
    }
}
