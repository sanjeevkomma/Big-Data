package com.demo.controller;

import com.demo.job.WordCountJob;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

@RestController
@RequestMapping("/api/mapreduce")
public class WordCountController {

    @PostMapping("/wordcount")
    public ResponseEntity<Map<String, Integer>> wordCount(@RequestParam("file") MultipartFile file) {
        try {
            // Save uploaded file to temp location
            File tempFile = File.createTempFile("input", ".txt");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(file.getBytes());
            }

            // Run MapReduce job
            Map<String, Integer> result = WordCountJob.runJob(tempFile.getAbsolutePath());

            // Delete temp file
            tempFile.delete();

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
