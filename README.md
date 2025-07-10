# Resilient Email Service (Spring Boot)

This project is a **Resilient Email Sending API** built using **Java + Spring Boot**.  
It uses two mock email providers and handles failures with **retry**, **fallback**, **rate limiting**, and **idempotency**.

---

## Features

- Send emails using mock providers (no real emails sent)
- Retry logic with exponential backoff
- Fallback to second provider if first fails
- Idempotency to avoid sending same email twice
- Rate limiting to control request flow
- Status tracking for each request

---

## Tech Stack

- Java 21
- Spring Boot
- Maven

---


## API Endpoint

```
POST /api/send-email
Content-Type: application/json
```

Request body:

```json
{
  "to": "test@example.com",
  "subject": "Hello",
  "body": "This is a test email",
  "idempotencyKey": "abc123"
}
```

Sample curl test:

curl -X POST http://localhost:8080/api/send-email \
 -H "Content-Type: application/json" \
 -d '{"to":"test@example.com","subject":"Hello","body":"Hi there!","idempotencyKey":"abc123"}'
 
## Assumptions

No actual emails are sent — mock providers simulate success/failure.

Duplicate emails are prevented using idempotencyKey.

Rate limit is configurable in code.

Fallback and retries are automatic.