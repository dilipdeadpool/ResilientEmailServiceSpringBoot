package com.example.email.service;

import com.example.email.model.EmailRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final List<EmailProvider> providers;
    private final EmailStatusTracker tracker = new EmailStatusTracker();
    private final RateLimiter limiter = new RateLimiter(5, 60000);

    public EmailService(List<EmailProvider> providers) {
        this.providers = providers;
    }

    public boolean sendEmail(EmailRequest request) {
    if (request == null) {
        System.err.println("Request is null");
        return false;
    }

    if (request.getIdempotencyKey() == null || 
        request.getTo() == null || 
        request.getSubject() == null || 
        request.getBody() == null) {
        System.err.println("Missing fields in request: " + request);
        return false;
    }

    if (tracker.isAlreadySent(request.getIdempotencyKey())) return false;
    if (!limiter.allow()) return false;

    for (int i = 0; i < providers.size(); i++) {
        if (tryWithRetry(providers.get(i), request)) {
            tracker.markAsSent(request.getIdempotencyKey());
            return true;
        }
    }
    return false;
}


    private boolean tryWithRetry(EmailProvider provider, EmailRequest request) {
        int attempts = 0;
        int maxAttempts = 3;
        long delay = 100;
        while (attempts < maxAttempts) {
            if (provider.send(request)) return true;
            attempts++;
            try { Thread.sleep(delay); } catch (InterruptedException ignored) {}
            delay *= 2;
        }
        return false;
    }
}