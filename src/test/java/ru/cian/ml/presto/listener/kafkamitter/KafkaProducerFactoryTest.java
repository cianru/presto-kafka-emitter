package ru.cian.ml.presto.listener.kafkamitter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

@RunWith(Parameterized.class)
public class KafkaProducerFactoryTest {
    private final Properties input;
    private final Properties expected;

    public KafkaProducerFactoryTest(Properties input, Properties expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        new Properties() {{
                            put("key.serializer", "org.apache.kafka.common.serialization.OtherSerializer");
                        }},
                        new Properties() {{
                            put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                            put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                        }}
                },
                {
                        new Properties() {{
                            put("bootstrap.servers", "localhost:6667");
                        }},
                        new Properties() {{
                            put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                            put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                            put("bootstrap.servers", "localhost:6667");
                        }}
                }

        });
    }

    @Test
    public void createProperties() {
        Assert.assertEquals(expected, new KafkaProducerFactory().addProperties(input));
    }
}
