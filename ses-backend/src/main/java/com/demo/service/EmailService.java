package com.demo.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class EmailService {

    private final SesClient sesClient;

    public EmailService() {
        this.sesClient = SesClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    public void sendEmail(String to, String subject, String message) {

        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();

        Content subjectContent = Content.builder()
                .data(subject)
                .build();

        Content bodyContent = Content.builder()
                .data(message)
                .build();

        Body body = Body.builder()
                .text(bodyContent)
                .build();

        Message msg = Message.builder()
                .subject(subjectContent)
                .body(body)
                .build();

        SendEmailRequest request = SendEmailRequest.builder()
                .source("vijaylog0414@gmail.com")
                .destination(destination)
                .message(msg)
                .build();

        sesClient.sendEmail(request);
    }
}
