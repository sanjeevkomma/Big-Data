package com.demo.controller;

import com.demo.bigdata.spark.SparkWordCount;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spark")
public class SparkController {

    @GetMapping("/wordcount")
    public String runSparkJob() {
        String inputPath = "input.txt";  // Change this path
        String outputPath = "output";   // Change this path

        try {
            SparkWordCount.main(new String[]{inputPath, outputPath});
            return "Spark Job Executed Successfully!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}