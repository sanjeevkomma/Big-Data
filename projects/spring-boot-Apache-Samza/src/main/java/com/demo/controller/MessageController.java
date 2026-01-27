package com.demo.controller;

import com.demo.samza.SamzaJob;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final SamzaJob samzaJob;

    public MessageController(SamzaJob samzaJob) {
        this.samzaJob = samzaJob;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        samzaJob.sendMessage(message);
        return "Message sent to Samza: " + message;
    }
}
