package com.demo.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private AmazonSimpleEmailService sesClient;

    private final String senderEmail = "vijaylog0414@gmail.com";

    public String sendEmail(String to, String subject, String message) {

        try {

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withSubject(new Content().withData(subject))
                            .withBody(new Body()
                                    .withText(new Content().withData(message))))
                    .withSource(senderEmail);

            sesClient.sendEmail(request);

            return "✅ Email Sent Successfully";

        } catch (Exception e) {

            return "❌ Email not sent. Email may not be verified in SES.";

        }
    }
}