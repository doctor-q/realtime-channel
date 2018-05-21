package cc.doctor.data.merger;

import cc.doctor.data.event.BinlogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by doctor on 17-8-31.
 * merge polled binlog if binlog catch so fast
 */
public class BinlogMerger extends AbstractMerger<BinlogEvent> {
    public static final Logger log = LoggerFactory.getLogger(BinlogMerger.class);

    /**
     * binlog 压缩，对于相同key的binlog进行合并
     */
    @Override
    public Iterable merge(Iterable binlogEvents) {
        Iterable<BinlogEvent> binlogEventIterable = (Iterable<BinlogEvent>) binlogEvents;
        List<BinlogEvent> merge = new LinkedList<>();
        Map<String, BinlogEvent> binlogEventMap = new HashMap<>();
        // 保证binlog的顺序
        Map<String, Integer> binlogSequence = new TreeMap<String, Integer>() {
            @Override
            public Comparator<? super String> comparator() {
                return new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return get(o1) - get(o2);
                    }
                };
            }
        };
        int seq = 0;
        for (BinlogEvent binlogEvent : binlogEventIterable) {
            String key = binlogEvent.getKey();
            BinlogEvent prev = binlogEventMap.get(key);
            if (prev == null) {
                binlogEventMap.put(key, binlogEvent);
                binlogSequence.put(key, seq++);
            } else {
                BinlogEvent merged = prev.merge(binlogEvent);
                if (merged == null) {   //delete operation
                    binlogEventMap.remove(key);
                } else {
                    binlogEventMap.put(key, merged);
                }
            }
        }
        for (String key : binlogSequence.keySet()) {
            BinlogEvent binlogEvent = binlogEventMap.get(key);
            merge.add(binlogEvent);
        }
        return merge;
    }
}
