package com.demo.controller;

import com.demo.service.HdfsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hdfs")
public class HdfsController {

    private final HdfsService hdfsService;

    public HdfsController(HdfsService hdfsService) {
        this.hdfsService = hdfsService;
    }

    @PostMapping("/write")
    public String write(
            @RequestParam String fileName,
            @RequestBody String content) throws Exception {
        return hdfsService.writeToHdfs(fileName, content);
    }

    @GetMapping("/read")
    public String read(@RequestParam String fileName) throws Exception {
        return hdfsService.readFromHdfs(fileName);
    }
}
