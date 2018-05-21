package cc.doctor.data.reader;

import cc.doctor.data.event.MixedBinlogEvent;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class MixedBinlogFilter extends BinlogFilter<MixedBinlogEvent> {
    private static final Logger log = LoggerFactory.getLogger(MixedBinlogFilter.class);

    @Override
    public Iterable<MixedBinlogEvent> filter(CanalEntry.Entry entry) {
        try {
            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            String sql = rowChange.getSql();
            MixedBinlogEvent mixedBinlogData = new MixedBinlogEvent();
            setBinlogEvent(mixedBinlogData, entry);
            mixedBinlogData.setSql(sql);
            return Collections.singletonList(mixedBinlogData);
        } catch (InvalidProtocolBufferException e) {
            log.error("", e);
        }
        return Collections.emptyList();
    }
}
