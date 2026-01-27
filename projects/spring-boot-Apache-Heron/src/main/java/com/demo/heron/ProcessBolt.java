package com.demo.heron;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class ProcessBolt extends BaseRichBolt {

    @Override
    public void prepare(Map<String, Object> conf,
                        TopologyContext context,
                        OutputCollector collector) {
    }

    @Override
    public void execute(Tuple input) {
        String message = input.getStringByField("message");
        System.out.println("🔥 Processed by Heron: " + message);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
