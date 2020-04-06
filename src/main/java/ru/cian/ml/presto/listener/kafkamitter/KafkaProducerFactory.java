package ru.cian.ml.presto.listener.kafkamitter;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class KafkaProducerFactory {

    private final static Logger logger = Logger.getLogger(KafkaProducerFactory.class.getName());

    static Map<String, String> immutableProperties = new HashMap<String, String>() {{
        put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }};


    public Properties addProperties(final Properties properties) {
        return new Properties() {{
            properties.forEach((k, v) -> setProperty(
                    (String) k, (String) v
            ));
            putAll(immutableProperties);
        }};
    }

    public KafkaProducer<String, String> create(final Properties properties) {
        Properties kafkaProperties = addProperties(properties);
        logger.info("Creating Kafka producer with properties " + kafkaProperties);
        return new KafkaProducer<>(kafkaProperties);
    }
}
