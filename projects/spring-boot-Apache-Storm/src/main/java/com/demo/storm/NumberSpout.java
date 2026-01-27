package com.demo.storm;

import org.apache.storm.tuple.Fields;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

// Spout to accept integers
public class NumberSpout implements IRichSpout {

    private SpoutOutputCollector collector;
    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public void nextTuple() {
        Integer number = queue.poll();
        if (number != null) {
            collector.emit(new Values(number));
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("number"));
    }

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    // Called by REST to add number
    public void addNumber(int number) {
        queue.offer(number);
    }

    // Unused methods
    public void close() {}
    public void activate() {}
    public void deactivate() {}
    public void ack(Object msgId) {}
    public void fail(Object msgId) {}
    public Map<String, Object> getComponentConfiguration() { return null; }
}

