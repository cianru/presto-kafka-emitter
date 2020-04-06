package ru.cian.ml.presto.listener.kafkamitter;

import com.facebook.presto.spi.eventlistener.EventListener;
import com.facebook.presto.spi.eventlistener.EventListenerFactory;

import java.util.Map;

public class PrestoEventsKafkaEmitterFactory implements EventListenerFactory {
    public String getName() {
        return "kafka-emitter";
    }

    public EventListener create(Map<String, String> config) {
        return new PrestoEventsKafkaEmitter(config);
    }
}
