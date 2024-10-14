package org.example.newdiary;

import org.example.newdiary.entity.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class Util {
    public Todo createTodoEntity(String description, Long listId) {
        TodoList todoList = createTodoListEntity("testList");
        Todo todo = Todo.builder()
                .description(description)
                .isDone(false)
                .todoList(todoList)
                .build();
        setId(todo, 1L);
        return todo;
    }

    public TodoList createTodoListEntity(String listName) {
        TodoList todoList = TodoList.builder().name(listName).build();
        setId(todoList, 1L);
        return todoList;
    }

    public <T extends Do> Activity createActivityEntity(T somethingDo, ActivityType type) {
        return Activity.builder()
                .type(type)
                .refId(somethingDo.getId())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    private void setId(Object entity, Long id) {
        try {
            Field idField = entity.getClass().getSuperclass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public Activity setTimeStamp(Activity entity, LocalDateTime date) {
        try {
            Field dateField = entity.getClass().getDeclaredField("timeStamp");
            dateField.setAccessible(true);
            dateField.set(entity, date);
            return entity;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
