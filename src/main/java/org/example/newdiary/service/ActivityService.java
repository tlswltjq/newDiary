package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import org.example.newdiary.entity.*;
import org.example.newdiary.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public <T extends Do> Activity createActivity(T somethingDo, ActivityType type) {
        Activity activity = Activity.builder()
                .type(type)
                .refId(somethingDo.getId())
                .description(type + "하였습니다.")
                .timeStamp(LocalDateTime.now())
                .build();
        return activityRepository.save(activity);
    }

    public List<Activity> getActivitiesWithinMonths(int months) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now.minusMonths(months-1).with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDate = now.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return activityRepository.findActivitiesWithinMonths(startDate, endDate);
    }
}
