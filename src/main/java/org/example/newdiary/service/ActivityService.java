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
    private final TodoService todoService;
    private final TodoListService todoListService;

    //private 이어야 하지 않은가
    public <T extends Do> Activity createActivity(T a, ActivityType type) {
        Activity activity = Activity.builder()
                .type(type)
                .refId(a.getId())
                .timeStamp(LocalDateTime.now())
                .build();
        return activityRepository.save(activity);
    }

    public Activity addTodoActivity(Long todoId, ActivityType type) {
        Todo todo = todoService.getTodo(todoId);
        return createActivity(todo, type);
    }

    public Activity addTodoListActivity(Long listId, ActivityType type){
        TodoList list = todoListService.getTodoList(listId);
        return createActivity(list, type);
    }
}
