package cc.doctor.data.consumer;

import cc.doctor.data.event.Event;

import java.util.UUID;

public class UUIDGenerator implements IdGenerator {
    @Override
    public String newId(Event event) {
        return UUID.randomUUID().toString();
    }
}
