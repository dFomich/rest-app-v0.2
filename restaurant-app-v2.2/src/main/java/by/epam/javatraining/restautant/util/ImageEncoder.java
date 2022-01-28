package by.epam.javatraining.restautant.util;

import java.io.ByteArrayOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;



public class ImageEncoder {
    private static final Logger LOGGER = LogManager.getLogger(ImageEncoder.class);

    private ImageEncoder() {
    }

    private static class ImageEncoderHolder {
        private static final ImageEncoder INSTANCE = new ImageEncoder();
    }

    public static ImageEncoder getInstance() {
        return ImageEncoderHolder.INSTANCE;
    }

    public String encodeImageToBase64String(Blob image) {
        String base64Image = null;

        try (InputStream inputStream = image.getBinaryStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }

        return base64Image;
    }
}
