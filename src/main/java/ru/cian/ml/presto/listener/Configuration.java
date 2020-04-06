package ru.cian.ml.presto.listener;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;


public class Configuration {
    private final static String PLUGIN_PREFIX = "kafka-emitter.";
    private final Map<String, String> config;

    public Configuration(Map<String, String> config) {
        this.config = Configuration.extractByPrefix(config, PLUGIN_PREFIX);
    }

    private static <T> Map<String, T> extractByPrefix(Map<String, T> config, String prefix) {
        int prefixLen = prefix.length();
        return config.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .collect(Collectors.toMap(
                        x -> x.getKey().substring(prefixLen),
                        Map.Entry::getValue
                ));
    }

    public Properties getConfig(final String section) {
        return new Properties() {{
            Configuration
                    .extractByPrefix(config, section + ".")
                    .forEach(this::put);
        }};
    }
}
