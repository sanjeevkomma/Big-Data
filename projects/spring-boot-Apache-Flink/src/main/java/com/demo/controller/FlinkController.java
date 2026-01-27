package com.demo.controller;

import com.demo.flink.SumJob;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flink")
public class FlinkController {

    @PostMapping("/sum")
    public String sum(@RequestBody List<Integer> numbers) throws Exception {

        SumJob.execute(numbers);

        return "Flink job triggered successfully";
    }
}
