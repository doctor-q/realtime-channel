package cc.doctor.data.consumer;

import cc.doctor.data.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by doctor on 17-9-1.
 */
public class LogTrigger extends Trigger {
    public static final Logger log = LoggerFactory.getLogger(LogTrigger.class);

    @Override
    public String name() {
        return "log";
    }

    @Override
    public void processData(Event event) {
        log.info(event.toString());
    }
}
