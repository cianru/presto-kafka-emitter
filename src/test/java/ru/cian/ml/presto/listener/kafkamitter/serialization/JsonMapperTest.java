package ru.cian.ml.presto.listener.kafkamitter.serialization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;


@RunWith(PowerMockRunner.class)
@PrepareForTest(Message.class)
public class JsonMapperTest {

    private final static ZonedDateTime zonedDateTime = ZonedDateTime.of(
            2020, 4, 7, 11, 35, 0, 0,
            ZoneId.of("Europe/Moscow")
    );


    @Before
    public void mockNow() {
        PowerMockito.mockStatic(ZonedDateTime.class);
        BDDMockito.given(ZonedDateTime.now(ZoneOffset.UTC)).willReturn(zonedDateTime);
    }

    @Test
    public void convertSimple() {
        A input = new A("1", "2");
        String expected = "{\"a\":\"1\",\"b\":\"2\",\"dateInserted\":\"2020-04-07T11:35:00+03:00\"}";
        Assert.assertEquals(expected, new JsonMapper().convert(input));
    }

    @Test
    public void convertNested() {
        B input = new B(new A("1", "2"), "3");
        String expected = "{\"a\":{\"a\":\"1\",\"b\":\"2\"},\"c\":\"3\",\"dateInserted\":\"2020-04-07T11:35:00+03:00\"}";
        Assert.assertEquals(expected, new JsonMapper().convert(input));
    }

    public class A {
        private final String a;
        private final String b;

        public A(String a, String b) {
            this.a = a;
            this.b = b;
        }

        public String getA() {
            return a;
        }

        public String getB() {
            return b;
        }
    }

    public class B {
        private final A a;
        private final String c;


        public B(A a, String c) {
            this.a = a;
            this.c = c;
        }

        public A getA() {
            return a;
        }

        public String getC() {
            return c;
        }
    }
}
