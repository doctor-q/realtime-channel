package cc.doctor.data.merger;

import cc.doctor.data.event.Event;
import cc.doctor.data.queue.Queue;
import cc.doctor.data.queue.QueueProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractMerger<T extends Event> implements Merger {
    private static final Logger log = LoggerFactory.getLogger(AbstractMerger.class);

    private Queue<T> mergeQueue;
    private int pollSize;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private QueueProducer logQueueProducer;
    private boolean stop;

    public void doMerge() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    List<T> events = mergeQueue.pollBatchAtMost(pollSize);
                    log.info("Poll data size [{}]", events.size());
                    Iterable<T> merges = merge(events);
                    for (T merge : merges) {
                        logQueueProducer.produce(merge);
                    }
                }
            }
        });
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public void onInit() {

    }

    @Override
    public void run() {
        doMerge();
    }

    @Override
    public void onDestroy() {
        stop = false;
    }
}
