package org.example.newdiary.dto;

import lombok.Data;
import org.example.newdiary.entity.TodoList;

import java.util.List;

@Data
public class GetTodoListResponse {
    final String title;
    final List<GetTodoResponse> todoList;

    public GetTodoListResponse(final TodoList todoList) {
        this.title = todoList.getName();
        this.todoList = todoList.getTodoList().stream().map(GetTodoResponse::new).toList();
    }
}
