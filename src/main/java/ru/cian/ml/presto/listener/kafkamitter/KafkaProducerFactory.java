package ru.cian.ml.presto.listener.kafkamitter;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class KafkaProducerFactory {
    
    static Map<String, String> overriddenProperties = new HashMap<String, String>() {{
        put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }};

    private final Logger logger = Logger.getLogger(getClass().getName());

    public Properties complementProperties(final Properties properties) {
        return new Properties(){{
            properties.forEach((k, v) -> setProperty(
                    (String) k, (String) v
            ));
            putAll(overriddenProperties);
        }};
    }

    public KafkaProducer<String, String> create(final Properties properties) {
        Properties kafkaProperties = complementProperties(properties);
        logger.info("Creating Kafka producer with properties " + kafkaProperties);
        return new KafkaProducer<>(kafkaProperties);
    }
}
