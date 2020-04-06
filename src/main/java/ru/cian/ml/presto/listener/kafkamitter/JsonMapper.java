package ru.cian.ml.presto.listener.kafkamitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonMapper {
    private final ObjectMapper objectMapper = new ObjectMapper() {{
        registerModule(new Jdk8Module()); // Optional support
    }};

    private final Logger logger = Logger.getLogger(getClass().getName());

    public String convert(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Unable to convert " + objectMapper + " to JSON", e);
            return null;
        }
    }
}
