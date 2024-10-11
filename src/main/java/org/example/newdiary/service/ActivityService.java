package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import org.example.newdiary.entity.*;
import org.example.newdiary.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public <T extends Do> Activity createActivity(T somethingDo, ActivityType type) {
        Activity activity = Activity.builder()
                .type(type)
                .refId(somethingDo.getId())
                .timeStamp(LocalDateTime.now())
                .build();
        return activityRepository.save(activity);
    }
}
