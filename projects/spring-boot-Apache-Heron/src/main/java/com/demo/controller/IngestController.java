package com.demo.controller;

import com.demo.heron.MessageSpout;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ingest")
public class IngestController {

    @PostMapping
    public String ingest(@RequestBody String message) {
        MessageSpout.enqueue(message);
        return "Message sent to Heron: " + message;
    }
}
