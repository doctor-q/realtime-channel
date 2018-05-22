package cc.doctor.data.event;

import java.io.Serializable;

public interface Event extends Cloneable, Serializable {
    Event clone();
}
