package cc.doctor.data.event;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.Map;

public class RowBinlogEvent extends BinlogEvent<RowBinlogEvent> {
    private Map<String, String> beforeFields;
    private Map<String, String> afterFields;
    private CanalEntry.EventType eventType;

    public Map<String, String> getBeforeFields() {
        return beforeFields;
    }

    public void setBeforeFields(Map<String, String> beforeFields) {
        this.beforeFields = beforeFields;
    }

    public Map<String, String> getAfterFields() {
        return afterFields;
    }

    public void setAfterFields(Map<String, String> afterFields) {
        this.afterFields = afterFields;
    }

    public CanalEntry.EventType getEventType() {
        return eventType;
    }

    public void setEventType(CanalEntry.EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public RowBinlogEvent merge(RowBinlogEvent after) {
        if (after.eventType.equals(CanalEntry.EventType.DELETE)) {
            return null;
        }
        if (after.eventType.equals(CanalEntry.EventType.UPDATE)) {
            this.afterFields = after.afterFields;
            this.eventType = after.eventType;
        }
        return after;
    }

    @Override
    public Event clone() {
        super.clone();
        return null;
    }
}
