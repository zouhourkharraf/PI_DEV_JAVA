package tn.magicbook.api;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


public class EmailSender {
    
        public static void sendEmail(String toSend,String subject,String msg) throws EmailException {

        String userEmail = "magicbook.pi@gmail.com";
        String userPassword = "yxofncvbpbizxjwk";

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(userEmail, userPassword));
        email.setSSLOnConnect(true);
        email.setFrom(userEmail);
        email.addTo(toSend);
        email.setSubject(subject);
        email.setMsg(msg);

        email.send();

        System.out.println("Email sent successfully.");
    }
}
