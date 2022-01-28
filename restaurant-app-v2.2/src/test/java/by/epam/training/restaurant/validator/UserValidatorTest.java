package by.epam.training.restaurant.validator;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.epam.javatraining.restautant.entity.User;
import by.epam.javatraining.restautant.validator.UserValidator;

public class UserValidatorTest {
    private User user;

    private UserValidator validator = UserValidator.INSTANCE;

    @Before
    public void init() {
    }

    @Test
    public void testValidateLoginWithNullEnterParameterShouldFalseResult() {
        String login = null;

        boolean result = validator.validateLogin(login);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateLoginWhichDoesNotMatchRegexShouldFalseResult() {
        String login = "ky";

        boolean result = validator.validateLogin(login);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateLoginWithValidParametersShouldFalseTrue() {
        String login = "userTest1";

        boolean result = validator.validateLogin(login);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidatePasswordWithNullEnterParameterShouldFalseResult() {
        String password = null;

        boolean result = validator.validatePassword(password);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidatePasswordWhichDoesNotMatchRegexShouldFalseResult() {
        String password = "easyP";

        boolean result = validator.validatePassword(password);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidatePasswordWithValidParameterShouldTrueResult() {
        String password = "Passtest321";

        boolean result = validator.validatePassword(password);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithNullEnterParameterShouldFalseResult() {
        user = null;

        boolean result = validator.validateUser(user);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithNotValidEmailShouldFalseResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375292999615");
        user.setEmail("notvalid@com");

        boolean result = validator.validateUser(user);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithValidEmailShouldTrueResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375292999615");
        user.setEmail("valid@gmail.com");

        boolean result = validator.validateUser(user);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithNotValidPhoneNumberShouldFalseResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+3752929");
        user.setEmail("valid@gmail.com");

        boolean result = validator.validateUser(user);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithValidPhoneNumberPlus37529ShouldTrueResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375297799615");
        user.setEmail("valid@gmail.com");

        boolean result = validator.validateUser(user);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithValidPhoneNumberPlus37517ShouldTrueResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375177799615");
        user.setEmail("valid@gmail.com");

        boolean result = validator.validateUser(user);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithNotValidFirstNameShouldFalseResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375297799615");
        user.setEmail("valid@gmail.com");
        user.setFirstName("A");

        boolean result = validator.validateUser(user);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithValidFirstNameShouldTrueResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375297799615");
        user.setEmail("valid@gmail.com");
        user.setFirstName("Aleh");

        boolean result = validator.validateUser(user);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithNotValidSecondNameShouldFalseResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375297799615");
        user.setEmail("valid@gmail.com");
        user.setFirstName("Aleh");
        user.setLastName("K");

        boolean result = validator.validateUser(user);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testValidateUserWithValidSecondNameShouldTrueResult() {
        user = new User();
        user.setLogin("user123");
        user.setPassword("user123");
        user.setPhoneNumber("+375297799615");
        user.setEmail("valid@gmail.com");
        user.setFirstName("Aleh");
        user.setLastName("Kastsiukovich");

        boolean result = validator.validateUser(user);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }
}
