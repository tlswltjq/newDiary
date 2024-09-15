package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.repository.TodoListRepository;
import org.example.newdiary.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoListRepository todoListRepository;

    public Todo createTodo(String todoTitle) {
        TodoList todoList = TodoList.builder()
                .listName("NewTodo!")
                .build();
        todoListRepository.save(todoList);

        Todo newTodo = Todo.builder()
                .todoTitle(todoTitle)
                .done(false)
                .listId(todoList)
                .build();
        return todoRepository.save(newTodo);
    }

    public Todo getTodo(Integer todoId) {
        return todoRepository.findById(todoId).orElseThrow(()->new NoSuchElementException("Wrong TodoId"));
    }
}
