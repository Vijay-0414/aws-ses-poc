package com.demo.controller;

import com.demo.model.EmailRequest;
import com.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) {

        return emailService.sendEmail(
                request.getTo(),
                request.getSubject(),
                request.getMessage()
        );
    }
}

