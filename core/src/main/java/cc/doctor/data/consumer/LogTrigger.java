package cc.doctor.data.consumer;

import cc.doctor.data.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by doctor on 17-9-1.
 */
public class LogTrigger extends Trigger {
    private Class logClass;
    private String logName;
    private Logger log;

    @Override
    public String name() {
        return "log";
    }

    @Override
    public void processData(Event event) {
        getLogger().info(event.toString());
    }

    public Logger getLogger() {
        if (log == null) {
            if (logClass != null) {
                log = LoggerFactory.getLogger(logClass);
            } else if (logName != null) {
                log = LoggerFactory.getLogger(logName);
            } else {
                log = LoggerFactory.getLogger(LogTrigger.class);
            }
        }
        return log;
    }
}
