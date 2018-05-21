package cc.doctor.data.queue;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by doctor on 17-9-1.
 */
public class ObjectSerializer<T extends Serializable> implements Serializer<T> {
    public static final Logger log = LoggerFactory.getLogger(ObjectSerializer.class);

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, T data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            log.error("", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void close() {

    }
}
