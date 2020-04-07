package ru.cian.ml.presto.listener.kafkamitter.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonMapper {
    private final static Logger logger = Logger.getLogger(JsonMapper.class.getName());
    private final ObjectMapper objectMapper = new ObjectMapper() {{
        registerModule(new Jdk8Module()); // Optional support
    }};

    public <T> String convert(T obj) {
        try {
            Message<T> message = new Message<>(obj);
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Unable to convert " + objectMapper + " to JSON", e);
            return null;
        }
    }
}
