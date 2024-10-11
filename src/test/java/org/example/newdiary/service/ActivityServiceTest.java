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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}