package az.edu.compar.services;

public interface EmailService {
    void sendEmail(String email, String subject, String text);
}
