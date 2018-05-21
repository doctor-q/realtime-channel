package cc.doctor.data.consumer;

import cc.doctor.data.event.Event;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并行触发器，对一个相同的event同时操作
 */
public class ParallelTrigger extends Trigger {
    private List<Trigger> triggers;
    private ExecutorService executorService;

    @Override
    public void processData(Event event) {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(triggers.size());
            for (Trigger trigger : triggers) {
                Event clone = event.clone();
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        trigger.beforeProcess(clone);
                        trigger.processData(clone);
                        trigger.afterProcess(clone);
                    }
                });
            }
        }
    }
}
