package org.example.newdiary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newdiary.dto.GetActivityResponse;
import org.example.newdiary.entity.Activity;
import org.example.newdiary.entity.ActivityType;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.service.ActivityService;
import org.example.newdiary.service.TodoListService;
import org.example.newdiary.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;
    private final TodoService todoService;
    private final TodoListService todoListService;

    @GetMapping
    public ResponseEntity<?> getActivity(@RequestParam(name = "months", defaultValue = "6") Integer months) { //확장 시 유저 받아야 함
        List<Activity> activityList = activityService.getActivitiesWithinMonths(months);
        List<GetActivityResponse> response = activityList.stream().map(activity -> {
            ActivityType.Category category = activity.getType().getCategory();
            Long refId = activity.getRefId();

            if (category == ActivityType.Category.TODO){
                Todo todo = todoService.getTodo(refId);
                return new GetActivityResponse(activity, todo);

//            }else if (category == ActivityType.Category.TODOLIST){    //확장시 이와같이 검사 필요
            }else{
                TodoList todoList = todoListService.getTodoList(refId);
                return new GetActivityResponse(activity, todoList);
            }
        }).toList();

        return ResponseEntity.ok().body(response);
    }
}
