package by.epam.javatraining.restautant.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class EmailSender {
    private static final Logger LOGGER = LogManager.getLogger(EmailSender.class);
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE = "email.properties";
    private static final String SENDER_KEY = "mail.smtp.user";
    private static final String PASSWORD_KEY = "mail.smtp.password";
    private static final String MAIL_SUBJECT = "Your order completed successfully!";

    private final String sender;
    private final String password;

    private EmailSender() {
        try {
            PROPERTIES.load(EmailSender.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e) {
            LOGGER.error(e);
        }

        sender = PROPERTIES.getProperty(SENDER_KEY);
        password = PROPERTIES.getProperty(PASSWORD_KEY);
    }

    private static class EmailSenderHolder {
        private static final EmailSender INSTANCE = new EmailSender();
    }

    public static EmailSender getInstance() {
        return EmailSenderHolder.INSTANCE;
    }

    public void sendEmail(String text, String toEmail){
        Session session = Session.getInstance(PROPERTIES, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(MAIL_SUBJECT);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
