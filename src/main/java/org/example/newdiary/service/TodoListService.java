package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;

    public TodoList createTodoList(String name){
        TodoList todoList = TodoList.builder()
                .name(name)
                .build();
        return todoListRepository.save(todoList);
    }

    public TodoList getTodoList(Long listId) {
        return todoListRepository.findById(listId).orElseThrow(() -> new NoSuchElementException("Wrong ListId"));
    }

    public TodoList updateTodoListName(String newName, Long id) {
        TodoList todoList = getTodoList(id);
        TodoList updated = todoList.updateName(newName);
        return todoListRepository.save(updated);
    }

    public void deleteTodoList(Long id) {
        todoListRepository.deleteById(id);
    }
}
