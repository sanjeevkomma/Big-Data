package com.demo.controller;

import com.demo.service.KuduService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kudu")
public class KuduController {

    private final KuduService kuduService;

    public KuduController(KuduService kuduService) {
        this.kuduService = kuduService;
    }

    @PostMapping("/insert")
    public String insert(@RequestParam int id, @RequestParam String name) {
        return kuduService.insertUser(id, name);
    }

    @GetMapping("/users")
    public List<Map<String, Object>> users() {
        return kuduService.getUsers();
    }
}
