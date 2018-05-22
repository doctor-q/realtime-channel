package cc.doctor.data.consumer;

import cc.doctor.data.event.Event;

import java.util.List;

/**
 * 触发器链，对同一个event进行链式操作
 */
public class TriggerChain extends Trigger {
    private List<Trigger> triggers;

    @Override
    public String name() {
        return "chained";
    }

    @Override
    public void processData(Event event) {
        for (Trigger trigger : triggers) {
            trigger.beforeProcess(event);
            trigger.processData(event);
            trigger.afterProcess(event);
        }
    }
}
