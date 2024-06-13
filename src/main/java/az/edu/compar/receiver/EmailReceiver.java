package az.edu.compar.receiver;


import az.edu.compar.payloads.EmailSendConsumer;
import az.edu.compar.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EmailReceiver {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "email-send-consumer")
    public void handleMessage(String message) {
        try {
            EmailSendConsumer emailSendConsumer = objectMapper.readValue(message, EmailSendConsumer.class);
            emailService.sendEmail(emailSendConsumer.getEmail(),emailSendConsumer.getSubject(),emailSendConsumer.getText());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
