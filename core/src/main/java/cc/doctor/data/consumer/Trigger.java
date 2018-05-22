package cc.doctor.data.consumer;


import cc.doctor.data.Named;
import cc.doctor.data.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by doctor on 17-8-31.
 * consume from the log queue and trigger an action
 */
public abstract class Trigger implements Named {
    private static final Logger log = LoggerFactory.getLogger(Trigger.class);

    public Trigger() {
    }

    public void beforeProcess(Event event) {

    }

    public abstract void processData(Event event);

    public void afterProcess(Event event) {
    }
}
