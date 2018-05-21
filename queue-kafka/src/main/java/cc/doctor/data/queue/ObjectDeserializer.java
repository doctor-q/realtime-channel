package cc.doctor.data.queue;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by doctor on 17-9-1.
 */
public class ObjectDeserializer<T extends Serializable> implements Deserializer<T> {
    public static final Logger log = LoggerFactory.getLogger(ObjectDeserializer.class);

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("", e);
        }
        return null;
    }

    @Override
    public void close() {

    }
}
