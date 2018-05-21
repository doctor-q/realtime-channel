package cc.doctor.data.queue;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by doctor on 17-8-21.
 * serializer map use object stream
 */
public class ObjectSerde<T extends Serializable> implements Serde<T> {
    public static final Logger log = LoggerFactory.getLogger(ObjectSerde.class);

    final private Serializer<T> serializer;
    final private Deserializer<T> deserializer;

    public ObjectSerde(Serializer<T> serializer, Deserializer<T> deserializer) {
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        serializer.configure(configs, isKey);
        deserializer.configure(configs, isKey);
    }

    @Override
    public void close() {
        serializer.close();
        deserializer.close();
    }

    @Override
    public Serializer<T> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<T> deserializer() {
        return deserializer;
    }

    public static <T extends Serializable> Serde<T> objectSerde() {
        ObjectSerializer<T> serializer = new ObjectSerializer<>();
        ObjectDeserializer<T> deserializer = new ObjectDeserializer<>();
        return new ObjectSerde<>(serializer, deserializer);
    }
}
