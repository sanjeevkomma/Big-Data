package com.demo.controller;

import com.demo.service.SparkJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SparkController {

    private final SparkJobService sparkJobService;

    public SparkController(SparkJobService sparkJobService) {
        this.sparkJobService = sparkJobService;
    }

    @GetMapping("/spark/run")
    public String runSparkJob() {
        long count = sparkJobService.processCsv();
        return "CSV processed. Row count = " + count;
    }
}
