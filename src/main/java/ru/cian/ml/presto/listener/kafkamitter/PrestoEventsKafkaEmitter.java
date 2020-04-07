package ru.cian.ml.presto.listener.kafkamitter;

import com.facebook.presto.spi.eventlistener.EventListener;
import com.facebook.presto.spi.eventlistener.QueryCompletedEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import ru.cian.ml.presto.listener.Configuration;
import ru.cian.ml.presto.listener.kafkamitter.serialization.JsonMapper;

import java.util.Map;
import java.util.Properties;

public class PrestoEventsKafkaEmitter implements EventListener {
    private final String queryCompletedTopic;
    private final KafkaProducer<String, String> kafkaProducer;
    private final JsonMapper jsonMapper = new JsonMapper();


    public PrestoEventsKafkaEmitter(Map<String, String> config) {
        Configuration configuration = new Configuration(config);

        Properties kafkaProperties = configuration.getConfig("kafka");
        kafkaProducer = new KafkaProducerFactory().create(kafkaProperties);

        Properties kafkaTopicProperties = configuration.getConfig("kafka-topics");
        queryCompletedTopic = kafkaTopicProperties.getProperty("query-completed");

    }

    public void queryCompleted(QueryCompletedEvent queryCompletedEvent) {
        String message = jsonMapper.convert(queryCompletedEvent);
        ProducerRecord<String, String> record = new ProducerRecord<>(queryCompletedTopic, message);
        kafkaProducer.send(record);
    }

}
