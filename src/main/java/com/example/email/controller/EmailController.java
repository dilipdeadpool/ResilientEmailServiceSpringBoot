package com.example.email.controller;

import com.example.email.model.EmailRequest;
import com.example.email.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
@GetMapping("/ping")
public ResponseEntity<String> ping() {
    return ResponseEntity.ok("App is running 🚀");
}
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        boolean result = emailService.sendEmail(request);
        if (result) {
            return ResponseEntity.ok("Email sent successfully");
        } else {
            return ResponseEntity.badRequest().body("Email sending failed or was duplicate");
        }
    }
}