package ru.cian.ml.presto.listener;

import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;


public class Configuration {
    private final static String NAME = "kafka-emitter";
    private final Map<String, String> config;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public Configuration(Map<String, String> config) {
        logger.info("Loading configuration " + config);
        this.config = config;
    }

    public Properties getConfig(final String section) {
        String prefix = NAME + "." + section + ".";
        int prefixLen = prefix.length();
        Properties properties = new Properties();

        config.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .forEach(entry -> properties.put(
                        entry.getKey().substring(prefixLen),
                        entry.getValue()
                ));
        return properties;
    }
}
