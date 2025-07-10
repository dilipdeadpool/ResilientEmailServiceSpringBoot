package com.example.email.service;

import java.util.concurrent.ConcurrentHashMap;

public class EmailStatusTracker {
    private final ConcurrentHashMap<String, Boolean> sentKeys = new ConcurrentHashMap<>();

    public boolean isAlreadySent(String key) {
        return sentKeys.containsKey(key);
    }

    public void markAsSent(String key) {
        sentKeys.put(key, true);
    }
}