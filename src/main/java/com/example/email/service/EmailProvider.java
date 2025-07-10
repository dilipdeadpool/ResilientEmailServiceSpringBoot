package com.example.email.service;

import com.example.email.model.EmailRequest;

public interface EmailProvider {
    boolean send(EmailRequest request);
}