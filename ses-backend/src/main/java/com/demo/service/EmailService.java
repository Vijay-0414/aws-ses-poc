package com.demo.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final AmazonSimpleEmailService sesClient;

    @Value("${app.mail.sender}")
    private String senderEmail;

    @Value("${app.mail.sender-name}")
    private String senderName;

    @Value("${app.mail.reply-to}")
    private String replyToEmail;

    public EmailService(AmazonSimpleEmailService sesClient) {
        this.sesClient = sesClient;
    }

    public String sendEmail(String to, String subject, String message) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withSubject(new Content().withData(subject))
                            .withBody(new Body()
                                    .withText(new Content().withData(message))))
                    .withSource(senderName + " <" + senderEmail + ">")
                    .withReplyToAddresses(replyToEmail);

            sesClient.sendEmail(request);

            return "✅ Email sent successfully";
        } catch (Exception e) {
            return "❌ Email not sent: " + e.getMessage();
        }
    }
}