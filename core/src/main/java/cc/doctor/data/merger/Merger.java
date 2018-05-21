package cc.doctor.data.merger;

import cc.doctor.data.Component;
import cc.doctor.data.event.Event;

public interface Merger<T extends Event> extends Component {
    Iterable<T> merge(Iterable<T> events);
}
