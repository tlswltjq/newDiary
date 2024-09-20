package org.example.newdiary.dto;

import lombok.Data;
import org.example.newdiary.entity.Todo;

@Data
public class GetTodoResponse {
    final Long id;
    final String descritpion;
    final Boolean isDone;

    public GetTodoResponse(Todo todo) {
        this.id = todo.getId();
        this.descritpion = todo.getDescription();
        this.isDone = todo.getIsDone();
    }
}
