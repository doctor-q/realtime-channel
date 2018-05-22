package cc.doctor.data.consumer;

import cc.doctor.data.Component;
import cc.doctor.data.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public abstract class AsyncBulkTrigger extends Trigger {
    private static final Logger log = LoggerFactory.getLogger(AsyncBulkTrigger.class);

    private int bulkSize;
    private List<Event> eventBuffer = new ArrayList<>(bulkSize);

    private BlockingQueue<Event> blockingQueue;

    @Override
    public void processData(Event event) {
        try {
            blockingQueue.put(event);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }

    abstract public void bulkProcessData (Iterable<Event> events);

    class BulkTrigger implements Component {
        private boolean stop;

        @Override
        public String name() {
            return null;
        }

        @Override
        public void onInit() {

        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    Event event = blockingQueue.take();
                    eventBuffer.add(event);
                    if (eventBuffer.size() >= bulkSize) {
                        bulkProcessData(eventBuffer);
                    }
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            }
        }

        @Override
        public void onDestroy() {
            stop = true;
            List<Event> remains = new LinkedList<>();
            remains.addAll(eventBuffer);
            blockingQueue.drainTo(remains);
            bulkProcessData(remains);
        }
    }
}
