package cc.doctor.data.event;

import com.alibaba.otter.canal.protocol.CanalEntry;

public abstract class BinlogEvent<T extends BinlogEvent> implements DispatchEvent {
    protected String instance;
    protected String database;
    protected String table;
    protected String primaryKey;
    protected String pkField;
    protected CanalEntry.EventType eventType;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getPkField() {
        return pkField;
    }

    public void setPkField(String pkField) {
        this.pkField = pkField;
    }

    public CanalEntry.EventType getEventType() {
        return eventType;
    }

    public void setEventType(CanalEntry.EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String dispatchKey() {
        return instance;
    }

    public String getKey() {
        return null;
    }

    public abstract T merge(T binlogEvent);
}
