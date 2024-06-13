package az.edu.compar.services.impls;

import az.edu.compar.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender emailSender;


    @Override
    public void sendEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ozella.von29@ethereal.email");
        message.setTo("ozella.von29@ethereal.email");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
