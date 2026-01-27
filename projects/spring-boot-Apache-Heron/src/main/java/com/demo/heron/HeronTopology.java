package com.demo.heron;

import org.apache.storm.Config;
import org.apache.storm.topology.TopologyBuilder;

public class HeronTopology {

    public static TopologyBuilder build() {

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("message-spout", new MessageSpout(), 1);
        builder.setBolt("process-bolt", new ProcessBolt(), 1)
                .shuffleGrouping("message-spout");

        return builder;
    }

    public static Config config() {
        Config config = new Config();
        config.setDebug(true);
        return config;
    }
}
