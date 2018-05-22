package cc.doctor.data.consumer;

import cc.doctor.data.event.Event;

public interface IdGenerator {
    String newId(Event event);
}
