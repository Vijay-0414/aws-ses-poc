package com.demo.controller;

import com.demo.model.EmailRequest;
import com.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/email")
=======
@RestController
@RequestMapping("/email")
@CrossOrigin
>>>>>>> d9f43618b8b7891257501993633119f6c5e80833
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) {

        emailService.sendEmail(
                request.getTo(),
                request.getSubject(),
                request.getMessage()
        );

        return "Email Sent Successfully";
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> d9f43618b8b7891257501993633119f6c5e80833
