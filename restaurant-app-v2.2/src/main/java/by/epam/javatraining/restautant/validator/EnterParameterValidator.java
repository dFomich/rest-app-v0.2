package by.epam.javatraining.restautant.validator;

public enum EnterParameterValidator {

    INSTANCE;

    private static final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";

    public boolean isNumeric(String requestParameter) {
        return requestParameter.matches(NUMBER_REGEX);
    }
}
