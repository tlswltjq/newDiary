package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import org.example.newdiary.entity.ActivityType;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.event.NewActivityEvent;
import org.example.newdiary.repository.TodoListRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final ApplicationEventPublisher eventPublisher;

    public TodoList createTodoList(String name){
        TodoList todoList = TodoList.builder()
                .name(name)
                .build();
        TodoList savedTodoList = todoListRepository.save(todoList);
        eventPublisher.publishEvent(new NewActivityEvent<>(this, savedTodoList, ActivityType.todoList_create));
        return savedTodoList;
    }

    public TodoList getTodoList(Long listId) {
        return todoListRepository.findById(listId).orElseThrow(() -> new NoSuchElementException("Wrong ListId"));
    }

    public TodoList updateTodoListName(String newName, Long id) {
        TodoList todoList = getTodoList(id);
        TodoList updated = todoList.updateName(newName);
        eventPublisher.publishEvent(new NewActivityEvent<>(this, updated, ActivityType.todoList_rename));
        return todoListRepository.save(updated);
    }

    public void deleteTodoList(Long id) {
        TodoList todoList = getTodoList(id);
        eventPublisher.publishEvent(new NewActivityEvent<>(this, todoList, ActivityType.todoList_delete));
        todoListRepository.delete(todoList);
    }
}
