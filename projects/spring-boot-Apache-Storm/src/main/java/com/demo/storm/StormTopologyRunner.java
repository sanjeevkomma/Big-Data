package com.demo.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class StormTopologyRunner {

    private static final NumberSpout spout = new NumberSpout();

    public static NumberSpout getSpout() { return spout; }

    public static void run() {
        try {
            TopologyBuilder builder = new TopologyBuilder();
            builder.setSpout("number-spout", spout);
            builder.setBolt("square-bolt", new SquareBolt())
                    .shuffleGrouping("number-spout");

            // Simple config
            Config conf = new Config();
            conf.setDebug(true);  // optional

            // LocalCluster without any metrics configuration
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("storm-poc", conf, builder.createTopology());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
