package com.example.email.model;

public class EmailRequest {
    private String to;
    private String subject;
    private String body;
    private String idempotencyKey;

    public EmailRequest() {}

    public EmailRequest(String to, String subject, String body, String idempotencyKey) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.idempotencyKey = idempotencyKey;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}