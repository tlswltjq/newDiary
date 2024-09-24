package org.example.newdiary.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoListService todoListService;

    public Todo createTodo(String description, Long listId) {
        TodoList todoList = todoListService.getTodoList(listId);
        Todo todo = Todo.builder()
                .description(description)
                .isDone(false)
                .todoList(todoList)
                .build();
        return todoRepository.save(todo);
    }


    public Todo getTodo(long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("Wrong TodoId"));
    }

    public Todo doneTodo(Long todoId){
        Todo todo = getTodo(todoId);
        return todoRepository.save(todo.done());
    }

    public Todo unDoneTodo(Long todoId){
        Todo todo = getTodo(todoId);
        return todoRepository.save(todo.unDone());
    }

    public Todo updateTodo(Long todoId, String newDescription) {
        Todo todo = getTodo(todoId);
        return todoRepository.save(todo.updateDescription(newDescription));
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}
