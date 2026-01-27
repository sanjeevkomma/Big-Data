package com.demo.controller;

import com.demo.storm.NumberSpout;
import com.demo.storm.StormTopologyRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NumberController {

    private final NumberSpout spout;

    public NumberController() {
        spout = new NumberSpout();
        // Only start Storm if not running on Windows (optional)
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            new Thread(() -> {
                try {
                    StormTopologyRunner.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @PostMapping("/submit")
    public String submitNumber(@RequestParam int number) {
        spout.addNumber(number);
        return "Number " + number + " submitted!";
    }
}