package cc.doctor.data.reader;

import cc.doctor.data.event.RowBinlogEvent;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RowBinlogFilter extends BinlogFilter<RowBinlogEvent> {
    private static final Logger log = LoggerFactory.getLogger(RowBinlogFilter.class);

    @Override
    public Iterable<RowBinlogEvent> filter(CanalEntry.Entry entry) {
        List<RowBinlogEvent> binlogEvents = new LinkedList<>();
        // 从配置获取主键
        ByteString storeValue = entry.getStoreValue();
        try {
            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
            for (CanalEntry.RowData rowData : rowDatasList) {
                List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                Map<String, String> beforeFields = columnListToMap(beforeColumnsList);
                Map<String, String> afterFields = columnListToMap(afterColumnsList);
                RowBinlogEvent rowBinlogEvent = new RowBinlogEvent();
                setBinlogEvent(rowBinlogEvent, entry);
                rowBinlogEvent.setBeforeFields(beforeFields);
                rowBinlogEvent.setAfterFields(afterFields);
                binlogEvents.add(rowBinlogEvent);
            }
        } catch (InvalidProtocolBufferException e) {
            log.error("", e);
        }
        return binlogEvents;
    }

    private Map<String, String> columnListToMap(List<CanalEntry.Column> columnsList) {
        Map<String, String> fields = new HashMap<>();
        if (columnsList != null && !columnsList.isEmpty()) {
            for (CanalEntry.Column column : columnsList) {
                String name = column.getName();
                String value = column.getValue();
                fields.put(name, value);
            }
        }
        return fields;
    }
}
