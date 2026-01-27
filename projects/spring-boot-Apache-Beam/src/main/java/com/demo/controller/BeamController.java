package com.demo.controller;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beam")
public class BeamController {

    @PostMapping("/sum")
    public String sumNumbers(@RequestBody List<Integer> numbers) {

        Pipeline pipeline = Pipeline.create();

        pipeline
                .apply(Create.of(numbers))
                .apply(Sum.integersGlobally())
                .apply(MapElements.into(TypeDescriptors.strings())
                        .via(sum -> {
                            System.out.println("SUM = " + sum);
                            return sum.toString();
                        }));

        pipeline.run();
        return "Beam job triggered";
    }



}
