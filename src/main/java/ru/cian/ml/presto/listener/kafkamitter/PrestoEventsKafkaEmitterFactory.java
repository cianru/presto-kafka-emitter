package ru.cian.ml.presto.listener.kafkamitter;

import com.facebook.presto.spi.eventlistener.EventListener;
import com.facebook.presto.spi.eventlistener.EventListenerFactory;

import java.util.Map;

public class PrestoEventsKafkaEmitterFactory implements EventListenerFactory {
    private final static String NAME = "kafka-emitter";

    public String getName() {
        return NAME;
    }

    public EventListener create(Map<String, String> config) {
        return new PrestoEventsKafkaEmitter(config);
    }
}
