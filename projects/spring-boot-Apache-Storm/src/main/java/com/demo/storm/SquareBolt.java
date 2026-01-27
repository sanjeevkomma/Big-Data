package com.demo.storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class SquareBolt implements IRichBolt {

    private OutputCollector collector;

    @Override
    public void prepare(Map<String, Object> stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        int number = input.getIntegerByField("number");
        int square = number * number;
        System.out.println("Number: " + number + ", Square: " + square);
        collector.ack(input);
    }

    @Override public void declareOutputFields(OutputFieldsDeclarer declarer) {}
    @Override public void cleanup() {}
    @Override public Map<String, Object> getComponentConfiguration() { return null; }
}
