package cc.doctor.data.consumer;

import cc.doctor.data.Component;
import cc.doctor.data.event.Event;
import cc.doctor.data.queue.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer implements Component {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private boolean stop;
    // 一个进程这个queue只能是一个，如果是多个trigger都需要使用这个queue，注意互斥
    private Queue<Event> logQueue;
    private Trigger trigger;

    public Queue<Event> getLogQueue() {
        return logQueue;
    }

    public void setLogQueue(Queue<Event> logQueue) {
        this.logQueue = logQueue;
    }

    @Override
    public String name() {
        return "consumer";
    }

    @Override
    public void onInit() {

    }

    @Override
    public void run() {
        consume();
    }

    @Override
    public void onDestroy() {

    }

    public void stop() {
        stop = true;
    }

    public void resume() {
        stop = false;
    }

    public void consume() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    Event event = logQueue.poll();
                    try {
                        trigger.beforeProcess(event);
                        trigger.processData(event);
                        trigger.afterProcess(event);
                    } catch (Exception e) {
                        // todo Here now we don't care process failed
                        log.error("", e);
                    }
                }
            }
        });
    }
}
