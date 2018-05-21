package cc.doctor.data.reader;

import cc.doctor.data.event.Event;

/**
 * source filter
 */
public interface ReaderFilter<T, E extends Event> {
    Iterable<E> filter(T t);
}
