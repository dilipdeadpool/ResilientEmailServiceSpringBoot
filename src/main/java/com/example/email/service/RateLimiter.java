package com.example.email.service;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class RateLimiter {
    private final int maxRequests;
    private final long intervalMillis;
    private final Queue<Long> requestTimes = new LinkedList<>();

    public RateLimiter(int maxRequests, long intervalMillis) {
        this.maxRequests = maxRequests;
        this.intervalMillis = intervalMillis;
    }

    public synchronized boolean allow() {
        long now = Instant.now().toEpochMilli();
        while (!requestTimes.isEmpty() && now - requestTimes.peek() > intervalMillis) {
            requestTimes.poll();
        }
        if (requestTimes.size() < maxRequests) {
            requestTimes.offer(now);
            return true;
        }
        return false;
    }
}