package com.demo.controller;

import com.demo.service.HiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hive")
public class HiveController {

    private final HiveService hiveService;

    public HiveController(HiveService hiveService) {
        this.hiveService = hiveService;
    }

    @GetMapping("/tables")
    public List<Map<String, Object>> getTables() {
        return hiveService.executeQuery("SHOW TABLES");
    }

    @GetMapping("/query")
    public List<Map<String, Object>> runQuery(@RequestParam String sql) {
        return hiveService.executeQuery(sql);
    }
}
