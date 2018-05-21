package cc.doctor.data.queue;

import cc.doctor.data.event.Event;

public class QueueProducer {
    private Queue<Event> queue;

    public QueueProducer() {
    }

    public QueueProducer(Queue<Event> queue) {
        this.queue = queue;
    }

    public void produce(Event event) {
        queue.push(event);
    }
}
