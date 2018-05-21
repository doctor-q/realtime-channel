package cc.doctor.data.reader;

import cc.doctor.data.event.Event;
import cc.doctor.data.queue.QueueProducer;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 从canal读取binglog
 */
public class CanalReader implements Reader<CanalReaderConfig> {
    private static final Logger log = LoggerFactory.getLogger(CanalReader.class);
    private Map<String, CanalConnector> canalConnectorMap = new HashMap<>();
    private QueueProducer mergeQueueProducer;
    private CanalReaderConfig readerConfig;
    private ReaderFilter readerFilter;

    @Override
    public void create(CanalReaderConfig readerConfig) {
        this.readerConfig = readerConfig;
        Preconditions.checkNotNull(readerConfig.getHost());
        Preconditions.checkNotNull(readerConfig.getPort());
        Preconditions.checkNotNull(readerConfig.getDestinations());
        for (String destination : readerConfig.getDestinations()) {
            log.info("Create canal connector [{}:{}:{}]", readerConfig.getHost(), readerConfig.getPort(), destination);
            CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(readerConfig.getHost(), readerConfig.getPort()),
                    destination, readerConfig.getUsername(), readerConfig.getPassword());
            canalConnectorMap.put(destination, connector);
        }
    }

    @Override
    public QueueProducer getQueueProducer() {
        return mergeQueueProducer;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public void onInit() {

    }

    @Override
    public void run() {
        startCanal();
    }

    @Override
    public void onDestroy() {

    }

    public void startCanal() {
            // create canal connector
            ExecutorService executorService = Executors.newFixedThreadPool(canalConnectorMap.size());
            for (String destination : canalConnectorMap.keySet()) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        final CanalConnector canalConnector = canalConnectorMap.get(destination);
                        canalConnector.connect();
                        canalConnector.subscribe();
                        canalConnector.rollback();
                        while (true) {
                            try {
                                Message message = canalConnector.getWithoutAck(readerConfig.getEntryPollSize());
                                long messageId = message.getId();
                                List<CanalEntry.Entry> entries = message.getEntries();
                                if (entries.isEmpty()) {
                                    try {
                                        Thread.sleep(1000);
                                        continue;
                                    } catch (InterruptedException ignored) {
                                    }
                                }
                                log.info("Read entries from [{}] size[{}]", destination, entries.size());
                                for (CanalEntry.Entry entry : entries) {
                                    CanalEntry.EntryType entryType = entry.getEntryType();
                                    //ignore transaction entry type and query event type
                                    if (entryType.equals(CanalEntry.EntryType.ROWDATA)) {
                                        Iterable<Event> events = readerFilter.filter(entry);
                                        for (Event event : events) {
                                            mergeQueueProducer.produce(event);
                                        }
                                    }
                                }
                                canalConnector.ack(messageId);
                            } catch (CanalClientException e) {
                                //re-connect
                                log.error("", e);
                                try {
                                    Thread.sleep(1000);
                                    canalConnector.connect();
                                    canalConnector.subscribe();
                                } catch (Exception ignored) {
                                    log.error("", e);
                                }
                            }
                        }
                    }
                });
            }
    }
}
