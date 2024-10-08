package org.example.newdiary.listener;

import lombok.RequiredArgsConstructor;
import org.example.newdiary.entity.ActivityType;
import org.example.newdiary.entity.Do;
import org.example.newdiary.event.NewActivityEvent;
import org.example.newdiary.service.ActivityService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewActivityEventListener {
    private final ActivityService activityService;

    @EventListener
    public <T extends Do> void handleNewActivityEvent(NewActivityEvent<T> event) {
        T entity = event.getEntity();
        ActivityType type = event.getType();

        activityService.createActivity(entity, type);
    }
}