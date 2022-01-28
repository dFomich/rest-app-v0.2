package by.epam.javatraining.restautant.util;

import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import by.epam.javatraining.restautant.exception.UtilException;



public class PasswordUtils {
    private static final Logger LOGGER = LogManager.getLogger(PasswordUtils.class);

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final byte[] SALT = {29, 49, -57, 117, -38, -26, -43, 118, -93, -117, 116, 45, 49, -99, 61, -72};

    private PasswordUtils() {
    }

    public static class PasswordUtilsHolder {
        private static final PasswordUtils INSTANCE = new PasswordUtils();
    }

    public static PasswordUtils getInstance() {
        return PasswordUtilsHolder.INSTANCE;
    }

    public String generateSecuredPassword(String password) throws UtilException {
        String securedPassword;
        byte[] securedPasswordBytes = hash(password.toCharArray());
        securedPassword = Base64.getEncoder().encodeToString(securedPasswordBytes);

        return securedPassword;
    }

    public boolean verifyUserPassword(String providedPassword, String securedPassword) throws UtilException {
        String newSecurePassword = generateSecuredPassword(providedPassword);

        return newSecurePassword.equalsIgnoreCase(securedPassword);
    }

    private byte[] hash(char[] password) throws UtilException {
        PBEKeySpec spec = new PBEKeySpec(password, SALT, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            return keyFactory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error(e);
            throw new UtilException(e);
        } finally {
            spec.clearPassword();
        }
    }
}
