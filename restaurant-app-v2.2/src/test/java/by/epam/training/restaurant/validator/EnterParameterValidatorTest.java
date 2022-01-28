package by.epam.training.restaurant.validator;


import org.junit.Assert;
import org.junit.Test;

import by.epam.javatraining.restautant.validator.EnterParameterValidator;

public class EnterParameterValidatorTest {
    private EnterParameterValidator validator = EnterParameterValidator.INSTANCE;

    @Test
    public void testIsNumericTextEnterParameterShouldFalseResult() {
        String text = "test";

        boolean result = validator.isNumeric(text);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testIsNumericTextPlusNumbersEnterParameterShouldFalseResult() {
        String text = "test14";

        boolean result = validator.isNumeric(text);
        boolean excepted = false;

        Assert.assertEquals(excepted, result);
    }

    @Test
    public void testIsNumericOnlyNumberEnterParameterShouldTrueResult() {
        String text = "1";

        boolean result = validator.isNumeric(text);
        boolean excepted = true;

        Assert.assertEquals(excepted, result);
    }
}
