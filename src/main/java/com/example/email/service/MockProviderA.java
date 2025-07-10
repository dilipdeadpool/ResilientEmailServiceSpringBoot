package com.example.email.service;

import com.example.email.model.EmailRequest;
import org.springframework.stereotype.Component;

@Component
public class MockProviderA implements EmailProvider {
    public boolean send(EmailRequest request) {
        return Math.random() > 0.2;
    }
}