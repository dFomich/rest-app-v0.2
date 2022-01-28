package by.epam.javatraining.restautant.exception;

public class JspException extends Exception {

    public JspException() {
    }

    public JspException(String message) {
        super(message);
    }

    public JspException(String message, Exception e) {
        super(message, e);
    }

    public JspException(Exception e) {
        super(e);
    }
}
