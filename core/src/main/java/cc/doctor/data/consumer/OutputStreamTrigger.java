package cc.doctor.data.consumer;

import cc.doctor.data.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

public abstract class OutputStreamTrigger extends Trigger {
    private static final Logger log = LoggerFactory.getLogger(OutputStreamTrigger.class);

    public abstract OutputStream outputStream();

    @Override
    public void processData(Event event) {
        OutputStream outputStream = outputStream();
        try {
            outputStream.write(event.toString().getBytes("UTF-8"));
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
