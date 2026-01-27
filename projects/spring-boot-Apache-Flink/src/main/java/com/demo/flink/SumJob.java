package com.demo.flink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;

import java.util.List;

public class SumJob {

    public static void execute(List<Integer> numbers) throws Exception {

        StreamExecutionEnvironment env =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Integer> stream = env.fromCollection(numbers);

        stream
                .keyBy(value -> 1)        // single global key
                .reduce(Integer::sum)
                .print("SUM RESULT");


        env.execute("Sum Numbers Job");
    }
}
