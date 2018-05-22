package cc.doctor.data.queue;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.*;

/**
 * Created by doctor on 17-8-31.
 */
public class KafkaQueue<T> extends Queue<T> {
    private Properties properties;
    private Producer<String, T> producer;
    private Consumer<String, T> consumer;
    private String topic;
    private long timeout;
    private LinkedList<T> queue = new LinkedList<>();

    public KafkaQueue(String topic, Map<String, String> kafkaProperties) {
        this.topic = topic;
        Properties props = new Properties();
        for (String key : kafkaProperties.keySet()) {
            props.put(key, kafkaProperties.get(key));
        }
        this.properties = props;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean push(T t) {
        if (producer == null) {
            producer = new KafkaProducer<>(properties);
        }
        producer.send(new ProducerRecord<>(topic, t));
        return true;
    }

    @Override
    public T poll() {
        if (queue.isEmpty()) {
            pollOnceFromKafka();
        }
        return queue.poll();
    }

    @Override
    public Collection<T> internalQueue() {
        return queue;
    }

    @Override
    public List<T> pollBatch(int size) {
        List<T> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(poll());
        }
        return list;
    }

    @Override
    public String type() {
        return "kafka";
    }

    private void pollOnceFromKafka() {
        while (queue.isEmpty()) {
            if (consumer == null) {
                consumer = new KafkaConsumer<>(properties);
                consumer.subscribe(Collections.singleton(topic));
            }
            ConsumerRecords<String, T> consumerRecords = consumer.poll(timeout);
            for (ConsumerRecord<String, T> consumerRecord : consumerRecords) {
                queue.push(consumerRecord.value());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public String name() {
        return "kafka";
    }
}
