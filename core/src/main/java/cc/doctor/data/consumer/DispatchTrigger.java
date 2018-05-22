package cc.doctor.data.consumer;

import cc.doctor.data.event.DispatchEvent;
import cc.doctor.data.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DispatchTrigger extends Trigger {
    private static final Logger log = LoggerFactory.getLogger(DispatchTrigger.class);

    private Map<String, Trigger> triggerMap = new HashMap<>();

    private Trigger createTrigger(String dispatchKey) {
        String triggerClass = null;
        try {
            Class<? extends Trigger> aClass = (Class<? extends Trigger>) Class.forName(triggerClass);
            return aClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.error("", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String name() {
        return "dispatch";
    }

    @Override
    public void processData(Event event) {
        String dispatchKey = ((DispatchEvent)event).dispatchKey();
        Trigger trigger = triggerMap.get(dispatchKey);
        if (trigger == null) {
            trigger = createTrigger(dispatchKey);
            triggerMap.put(dispatchKey, trigger);
        }
        trigger.beforeProcess(event);
        trigger.processData(event);
        trigger.afterProcess(event);
    }
}
