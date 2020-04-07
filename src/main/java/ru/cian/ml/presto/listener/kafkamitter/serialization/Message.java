package ru.cian.ml.presto.listener.kafkamitter.serialization;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Message<T> {
    @JsonUnwrapped
    private T event;
    private String dateInserted;

    public Message(T event) {
        this.event = event;
        this.dateInserted = currentDateTime();
    }

    private static String currentDateTime() {
        return ZonedDateTime.now(ZoneOffset.UTC)
                            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public T getEvent() {
        return event;
    }

    public String getDateInserted() {
        return dateInserted;
    }
}
