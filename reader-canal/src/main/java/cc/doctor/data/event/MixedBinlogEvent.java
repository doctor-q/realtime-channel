package cc.doctor.data.event;

public class MixedBinlogEvent extends BinlogEvent<MixedBinlogEvent> {
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public MixedBinlogEvent merge(MixedBinlogEvent binlogEvent) {
        return null;
    }

    @Override
    public Event clone() {
        return null;
    }
}
