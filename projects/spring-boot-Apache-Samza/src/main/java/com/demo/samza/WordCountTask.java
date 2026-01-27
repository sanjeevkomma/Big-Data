package com.demo.samza;

import org.apache.samza.task.StreamTask;
import org.apache.samza.task.MessageCollector;
import org.apache.samza.task.TaskCoordinator;
import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.config.Config;

public class WordCountTask implements StreamTask {

    @Override
    public void process(IncomingMessageEnvelope envelope,
                        MessageCollector collector,
                        TaskCoordinator coordinator) {
        String message = (String) envelope.getMessage();
        System.out.println("Samza consumed message: " + message);

        // You can emit processed messages to another output stream
        // collector.send(new OutgoingMessageEnvelope(...));
    }
}
