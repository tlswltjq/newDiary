package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(String todoTitle) {
        Todo newTodo = new Todo().builder()
                .todoTitle(todoTitle)
                .done(false)
                .build();
        return todoRepository.save(newTodo);
    }

    public Todo getTodo(Integer todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("Wrong Id"));
    }
//    public List<Todo> getTodoList(){}
//    public Todo updateTodo(){}
//    public Todo deleteTodo(){}
}
