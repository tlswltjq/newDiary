package org.example.newdiary.service;

import org.assertj.core.api.Assertions;
import org.example.newdiary.Util;
import org.example.newdiary.entity.Activity;
import org.example.newdiary.entity.ActivityType;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.repository.ActivityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Activity 관련 테스트")
@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {
    private Util util = new Util();
    @Mock
    ActivityRepository activityRepository;
    @InjectMocks
    ActivityService activityService;

    @DisplayName("Todo를 이용해 Activity를 남길 수 있다.")
    @ParameterizedTest
    @EnumSource(ActivityType.class)
    public void activityUsingTodoWithDifferentTypes(ActivityType type) {
        Todo todo = util.createTodoEntity("newTodo", 1L);
        Activity activity = util.createActivityEntity(todo, type);

        when(activityRepository.save(any(Activity.class))).thenReturn(activity);

        Activity result = activityService.createActivity(todo, type);

        verify(activityRepository).save(any(Activity.class));
        Assertions.assertThat(result.getRefId()).isEqualTo(todo.getId());
        Assertions.assertThat(result.getType()).isEqualTo(type);
    }

    @DisplayName("TodoList를 이용해 Activity를 남길 수 있다.")
    @ParameterizedTest
    @EnumSource(ActivityType.class)
    public void activityUsingTodoList(ActivityType type) {
        TodoList todoList = util.createTodoListEntity("testList");
        Activity activity = util.createActivityEntity(todoList, type);

        when(activityRepository.save(any(Activity.class))).thenReturn(activity);

        Activity result = activityService.createActivity(todoList, type);

        verify(activityRepository).save(any(Activity.class));
        Assertions.assertThat(result.getRefId()).isEqualTo(todoList.getId());
        Assertions.assertThat(result.getType()).isEqualTo(type);
    }

    @DisplayName("현재 월로부터 n간의 Activity를 조회할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void activityRetrievedByNMonthTest(int month) {
        LocalDateTime now = LocalDateTime.of(2024, 10, 10, 12, 30, 0);
        List<Activity> activities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            activities.add(util.setTimeStamp(util.createActivityEntity(util.createTodoEntity("newTodo" + i, 1L), ActivityType.todo_add), now.minusMonths(i)));
            activities.add(util.setTimeStamp(util.createActivityEntity(util.createTodoListEntity("testList" + i), ActivityType.todo_add), now.minusMonths(i)));
        }

        when(activityRepository.findActivitiesWithinMonths(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenAnswer(invocation -> {
                    LocalDateTime startDate = invocation.getArgument(0);
                    LocalDateTime endDate = invocation.getArgument(1);
                    return activities.stream()
                            .filter(activity -> !activity.getTimeStamp().isBefore(startDate) && !activity.getTimeStamp().isAfter(endDate))
                            .toList();
                });

        List<Activity> result = activityService.getActivitiesWithinMonths(month);

        verify(activityRepository).findActivitiesWithinMonths(any(LocalDateTime.class), any(LocalDateTime.class));
        Assertions.assertThat(result).hasSize(month*2);
    }
}