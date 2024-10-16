package org.example.newdiary.dto;

import lombok.Data;
import org.example.newdiary.entity.Activity;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.entity.TodoList;

import java.time.LocalDateTime;


@Data
public class GetActivityResponse {
    private final String doName;
    private final String type;
    private final LocalDateTime timeStamp;

    public GetActivityResponse(Activity activity, Todo todo) {
        this.doName = todo.getDescription();
        this.type = activity.getType().toString();
        this.timeStamp = activity.getTimeStamp();
    }

    public GetActivityResponse(Activity activity, TodoList list) {
        this.doName = list.getName();
        this.type = activity.getType().toString();
        this.timeStamp = activity.getTimeStamp();
    }
}
