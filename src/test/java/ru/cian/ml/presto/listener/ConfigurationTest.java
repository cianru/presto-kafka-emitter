package ru.cian.ml.presto.listener;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class ConfigurationTest {

    private final String input;
    private final Properties expected;

    private final Map<String, String> config = new HashMap<String, String>() {{
        put("kafka-emitter.kafka-topics.query-completed", "presto.query-completed");
        put("kafka-emitter.kafka.bootstrap.servers", "localhost:6667");
    }};

    public ConfigurationTest(String input, Properties expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"kafka", new Properties() {{
                    put("bootstrap.servers", "localhost:6667");
                }}},
                {"kafka-topics", new Properties() {{
                    put("query-completed", "presto.query-completed");
                }}}

        });
    }

    @Test
    public void getConfig() {
        Assert.assertEquals(expected, new Configuration(config).getConfig(input));
    }
}