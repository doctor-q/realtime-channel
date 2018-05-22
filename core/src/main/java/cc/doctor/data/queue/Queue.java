package cc.doctor.data.queue;

import cc.doctor.data.Named;

import java.util.Collection;
import java.util.List;

/**
 * Created by doctor on 17-8-31.
 */
public abstract class Queue<T> implements Named {
    /**
     * push data
     */
    public abstract boolean push(T t);

    /**
     * poll one data
     */
    public abstract T poll();

    /**
     * a queue internal which do buffer
     */
    public abstract Collection<T> internalQueue();

    /**
     * poll data blocked
     */
    public abstract List<T> pollBatch(int size);

    /**
     * poll data limit size
     */
    public List<T> pollBatchAtMost(int size) {
        int dataSize = internalQueue().size();
        if (dataSize == 0) {
            return pollBatch(1);
        } else if (dataSize < size) {
            return pollBatch(dataSize);
        } else {
            return pollBatch(size);
        }
    }

    public abstract String type();
}
