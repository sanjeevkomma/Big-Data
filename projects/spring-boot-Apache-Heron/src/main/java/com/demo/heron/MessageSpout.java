package com.demo.heron;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageSpout extends BaseRichSpout {

    private static final BlockingQueue<String> QUEUE =
            new LinkedBlockingQueue<>();

    private SpoutOutputCollector collector;

    public static void enqueue(String msg) {
        QUEUE.offer(msg);
    }

    @Override
    public void open(
            Map<String, Object> conf,
            TopologyContext context,
            SpoutOutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        String msg = QUEUE.poll();
        if (msg != null) {
            collector.emit(new Values(msg));
        }
    }

    @Override
    public void declareOutputFields(
            OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("message"));
    }
}
