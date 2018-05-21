package cc.doctor.data.queue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by doctor on 17-8-31.
 */
public class MemoryBlockQueue<T> extends Queue<T> {
    private BlockingQueue<T> blockingQueue;

    public MemoryBlockQueue(int capacity) {
        blockingQueue = new LinkedBlockingQueue<>(capacity);
    }

    @Override
    public boolean push(T t) {
        return blockingQueue.add(t);
    }

    @Override
    public T poll() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public Collection<T> internalQueue() {
        return blockingQueue;
    }

    @Override
    public List<T> pollBatch(int size) {
        List<T> ts = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            ts.add(poll());
        }
        return ts;
    }

    @Override
    public String type() {
        return "memory";
    }
}
