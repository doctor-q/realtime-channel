package cc.doctor.data.reader;

import cc.doctor.data.Component;
import cc.doctor.data.queue.QueueProducer;

/**
 * reader is the input
 */
public interface Reader<T extends ReaderConfig> extends Component {
    void create(T readerConfig);
    QueueProducer getQueueProducer();
}
