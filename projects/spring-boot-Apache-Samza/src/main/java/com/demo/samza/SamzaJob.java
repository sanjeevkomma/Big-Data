package com.demo.samza;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SamzaJob {

    private final Map<String, String> messages = new HashMap<>();

    public void sendMessage(String message) {
        // Simple processing: just log it
        System.out.println("SamzaJob received: " + message);
        messages.put(String.valueOf(messages.size() + 1), message);
    }

    public Map<String, String> getMessages() {
        return messages;
    }
}
