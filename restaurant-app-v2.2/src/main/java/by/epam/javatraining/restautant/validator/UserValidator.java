package by.epam.javatraining.restautant.validator;

import by.epam.javatraining.restautant.entity.User;

public enum UserValidator {

    INSTANCE;

    private static final String LOGIN_PATTERN = "^[A-Za-z0-9_-]{3,16}$";
    private static final String PASSWORD_PATTERN = "^[A-Za-z0-9_-]{6,100}$";
    private static final String EMAIL_PATTERN = "^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$";
    private static final String PHONE_NUMBER_PATTERN = "^\\+375(17|29|33|44)[0-9]{7}$";
    private static final String FIRST_NAME_PATTERN = "^[A-Za-zА-ЯЁа-яё]{2,}$";
    private static final String LAST_NAME_PATTERN = "^[A-Za-zА-ЯЁа-яё-]{2,}$";

    public boolean validateUser(User user) {
        return user != null
                && user.getLogin().matches(LOGIN_PATTERN)
                && user.getPassword().matches(PASSWORD_PATTERN)
                && user.getEmail().matches(EMAIL_PATTERN)
                && user.getPhoneNumber().matches(PHONE_NUMBER_PATTERN)
                && (user.getFirstName() == null || user.getFirstName().equals("")
                || user.getFirstName().matches(FIRST_NAME_PATTERN))
                && (user.getLastName() == null || user.getLastName().equals("")
                || user.getLastName().matches(LAST_NAME_PATTERN));
    }

    public boolean validateLogin(String login) {
        return login != null && login.matches(LOGIN_PATTERN);
    }

    public boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }
}
