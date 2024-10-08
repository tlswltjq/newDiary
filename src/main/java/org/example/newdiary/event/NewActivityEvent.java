package org.example.newdiary.event;

import lombok.Getter;
import org.example.newdiary.entity.ActivityType;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewActivityEvent<T> extends ApplicationEvent {
    private final T entity;
    private final ActivityType type;

    public NewActivityEvent(Object source, T entity, ActivityType type) {
        super(source);
        this.entity = entity;
        this.type = type;
    }

}
