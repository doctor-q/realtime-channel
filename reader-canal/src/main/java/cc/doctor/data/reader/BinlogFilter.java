package cc.doctor.data.reader;

import cc.doctor.data.event.BinlogEvent;
import com.alibaba.otter.canal.protocol.CanalEntry;

public abstract class BinlogFilter<T extends BinlogEvent> implements ReaderFilter<CanalEntry.Entry, T> {
    public void setBinlogEvent(BinlogEvent binlogEvent, CanalEntry.Entry entry) {
        CanalEntry.Header header = entry.getHeader();
        CanalEntry.EventType eventType = header.getEventType();
        String schemaName = header.getSchemaName();
        String tableName = header.getTableName();
        binlogEvent.setDatabase(schemaName);
        binlogEvent.setTable(tableName);
        binlogEvent.setEventType(eventType);
    }
}
