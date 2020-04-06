package ru.cian.ml.presto.listener;

import com.facebook.presto.spi.Plugin;
import com.facebook.presto.spi.eventlistener.EventListenerFactory;
import ru.cian.ml.presto.listener.kafkamitter.PrestoEventsKafkaEmitterFactory;

import java.util.Collections;

public class EventListenerPlugin implements Plugin {

    public Iterable<EventListenerFactory> getEventListenerFactories() {
        return Collections.singletonList(
                new PrestoEventsKafkaEmitterFactory()
        );
    }
}
